package kz.tim.samples.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.tim.samples.dto.News;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Documentation for {@code ${NAME}}.
 *
 * @author Timur Tibeyev.
 */
@WebServlet("crawl")
public class CrawlerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<News> news = new ArrayList<>();
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet request = new HttpGet("https://edition.cnn.com/sport");
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            Document doc;
            try {
                doc = Jsoup.parse(content);
                Elements ul = doc.select(".cn-list-hierarchical-xs");
                for (Element li : ul) {
                    Element contentDiv = li.getElementsByClass("cd__content")
                            .first();
                    if (contentDiv == null) {
                        continue;
                    }
                    Element headlineDiv = contentDiv.getElementsByClass("cd__headline-text")
                            .first();
                    Element ahref = contentDiv.getElementsByTag("a").first();

                    if (headlineDiv != null && ahref != null) {
                        String title = headlineDiv.text();
                        String newsUrl = "https://edition.cnn.com" + ahref.attr("href");
                        HttpGet specificNewsRequest = new HttpGet(newsUrl);
                        HttpResponse specificNewsResponse = client.execute(specificNewsRequest);
                        if (specificNewsResponse.getStatusLine().getStatusCode() == 200) {
                            String specificNewsContent =
                                    EntityUtils.toString(specificNewsResponse.getEntity(),
                                            "UTF-8");
                            Document specificNewsDoc;
                            try {
                                specificNewsDoc = Jsoup.parse(specificNewsContent);
                                Element authorSpan = specificNewsDoc
                                        .getElementsByClass("metadata__byline__author")
                                        .first();
                                String author = authorSpan.text();

                                Element updateDateSpan = specificNewsDoc
                                        .getElementsByClass("update-time")
                                        .first();
                                String updateDate = updateDateSpan.text();

                                news.add(new News(newsUrl, author, title, updateDate));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        client.close();

        req.setAttribute("news", news);
        req.getRequestDispatcher("crawler.jsp").forward(req, res);
    }

    /**
     * Responsible for processing GET request.
     * @param request http request
     * @param response http response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
