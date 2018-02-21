package kz.tim.samples.dto;

/**
 * Simple model to store results of the parsing.
 *
 * @author Timur Tibeyev.
 */
public class Occurrence {
    private String value;
    private String sheet;
    private int row;
    private int column;

    /**
     * Creates new instance of the {@code Occurrence} class.
     * @param value cell value
     * @param sheet sheet name
     * @param row row number
     * @param column column number
     */
    public Occurrence(String value, String sheet, int row, int column) {
        this.value = value;
        this.sheet = sheet;
        this.row = row + 1;
        this.column = column + 1;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
