package model.pojosToRead_XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name="temperatura")
public class Temperatura {

    private Municipio mun005;
    private Municipio mun006;
    private Municipio mun007;
    private Municipio mun009;
    private Municipio mun013;
    private Municipio mun014;
    private Municipio mun016;
    private Municipio mun045;
    private Municipio mun047;
    private Municipio mun049;
    private Municipio mun058;
    private Municipio mun065;
    private Municipio mun067;
    private Municipio mun074;
    private Municipio mun080;
    private Municipio mun092;
    private Municipio mun102;
    private Municipio mun120;
    private Municipio mun123;
    private Municipio mun133;
    private Municipio mun148;
    private Municipio mun161;
    private Municipio mun171;
    private Municipio mun180;
    private List<Municipio> municipios;



    //Constructor

    public Temperatura(Municipio mun005, Municipio mun006, Municipio mun007, Municipio mun009, Municipio mun013, Municipio mun014, Municipio mun016, Municipio mun045, Municipio mun047, Municipio mun049, Municipio mun058, Municipio mun065, Municipio mun067, Municipio mun074, Municipio mun080, Municipio mun092, Municipio mun102, Municipio mun120, Municipio mun123, Municipio mun133, Municipio mun148, Municipio mun161, Municipio mun171, Municipio mun180) {
        this.mun005 = mun005;
        this.mun006 = mun006;
        this.mun007 = mun007;
        this.mun009 = mun009;
        this.mun013 = mun013;
        this.mun014 = mun014;
        this.mun016 = mun016;
        this.mun045 = mun045;
        this.mun047 = mun047;
        this.mun049 = mun049;
        this.mun058 = mun058;
        this.mun065 = mun065;
        this.mun067 = mun067;
        this.mun074 = mun074;
        this.mun080 = mun080;
        this.mun092 = mun092;
        this.mun102 = mun102;
        this.mun120 = mun120;
        this.mun123 = mun123;
        this.mun133 = mun133;
        this.mun148 = mun148;
        this.mun161 = mun161;
        this.mun171 = mun171;
        this.mun180 = mun180;
        municipios = List.of(mun005,mun006,mun007,mun009,mun013,mun014,mun016,
                mun045,mun047,mun049,mun058,mun065,mun067,mun074,mun080,mun092,
                mun102,mun120,mun123,mun133,mun148,mun161,mun171,mun180);
    }

    public Temperatura() {
    }

//Getters And Setters

    @XmlElements(@XmlElement(name = "municipio",type =Municipio.class))
    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun005() {
        return mun005;
    }

    public void setMun005(Municipio mun005) {
        this.mun005 = mun005;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun006() {
        return mun006;
    }

    public void setMun006(Municipio mun006) {
        this.mun006 = mun006;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun007() {
        return mun007;
    }

    public void setMun007(Municipio mun007) {
        this.mun007 = mun007;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun009() {
        return mun009;
    }

    public void setMun009(Municipio mun009) {
        this.mun009 = mun009;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun013() {
        return mun013;
    }

    public void setMun013(Municipio mun013) {
        this.mun013 = mun013;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun014() {
        return mun014;
    }

    public void setMun014(Municipio mun014) {
        this.mun014 = mun014;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun016() {
        return mun016;
    }

    public void setMun016(Municipio mun016) {
        this.mun016 = mun016;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun045() {
        return mun045;
    }

    public void setMun045(Municipio mun045) {
        this.mun045 = mun045;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun047() {
        return mun047;
    }

    public void setMun047(Municipio mun047) {
        this.mun047 = mun047;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun049() {
        return mun049;
    }

    public void setMun049(Municipio mun049) {
        this.mun049 = mun049;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun058() {
        return mun058;
    }

    public void setMun058(Municipio mun058) {
        this.mun058 = mun058;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun065() {
        return mun065;
    }

    public void setMun065(Municipio mun065) {
        this.mun065 = mun065;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun067() {
        return mun067;
    }

    public void setMun067(Municipio mun067) {
        this.mun067 = mun067;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun074() {
        return mun074;
    }

    public void setMun074(Municipio mun074) {
        this.mun074 = mun074;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun080() {
        return mun080;
    }

    public void setMun080(Municipio mun080) {
        this.mun080 = mun080;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun092() {
        return mun092;
    }

    public void setMun092(Municipio mun092) {
        this.mun092 = mun092;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun102() {
        return mun102;
    }

    public void setMun102(Municipio mun102) {
        this.mun102 = mun102;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun120() {
        return mun120;
    }

    public void setMun120(Municipio mun120) {
        this.mun120 = mun120;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun123() {
        return mun123;
    }

    public void setMun123(Municipio mun123) {
        this.mun123 = mun123;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun133() {
        return mun133;
    }

    public void setMun133(Municipio mun133) {
        this.mun133 = mun133;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun148() {
        return mun148;
    }

    public void setMun148(Municipio mun148) {
        this.mun148 = mun148;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun161() {
        return mun161;
    }

    public void setMun161(Municipio mun161) {
        this.mun161 = mun161;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun171() {
        return mun171;
    }

    public void setMun171(Municipio mun171) {
        this.mun171 = mun171;
    }

    @XmlElement(name = "municipio",type =Municipio.class)
    public Municipio getMun180() {
        return mun180;
    }

    public void setMun180(Municipio mun180) {
        this.mun180 = mun180;
    }

}
