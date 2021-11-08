package model.pojosToRead_XML;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@XmlType(propOrder = {
        "minDate", "maxDate", "min", "max", "average"})
public class Medida {
    private String minDate;
    private String maxDate;
    private Double min;
    private Double max;
    private Double average;

    //Constructors


    public Medida(String minDate, String maxDate, Double min, Double max, Double average) {
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public Medida() {
    }

    //Getters And Setters
    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
