package service;

import model.Meteo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MeteoService {

    List<Meteo> data;
    private Double sumaMediaDias=0.0;

    public MeteoService(List<Meteo> data) {
        this.data = data;
    }


    public List<Meteo> dataPuntoMuestreo(String punto_muestreo) {

        return data.stream().filter(p -> p.getPuntoMuestreo().contains(punto_muestreo)).collect(Collectors.toList());
    }


    public String dataFechaIncio(List<Meteo> listaPuntoMuestreo) {
        if (listaPuntoMuestreo.isEmpty()) {
            return " ** Fecha no encontrada ** ";
        } else {
            int day = Integer.parseInt(listaPuntoMuestreo.stream().findFirst().get().getDia());
            int mon = Integer.parseInt(listaPuntoMuestreo.stream().findFirst().get().getMes());
            int year = Integer.parseInt(listaPuntoMuestreo.stream().findFirst().get().getAnio());
            LocalDate initDate = LocalDate.of(year, mon, day);
            DateTimeFormatter btf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return initDate.format(btf);
        }
    }

    public String dataFechaFin(List<Meteo> listaPuntoMuestreo) {
        if (listaPuntoMuestreo.isEmpty()) {
            return " ** Fecha no encontrada ** ";
        } else {
            int day = Integer.parseInt(listaPuntoMuestreo.get(listaPuntoMuestreo.size() - 1).getDia());
            int mon = Integer.parseInt(listaPuntoMuestreo.get(listaPuntoMuestreo.size() - 1).getMes());
            int year = Integer.parseInt(listaPuntoMuestreo.get(listaPuntoMuestreo.size() - 1).getAnio());
            LocalDate initDate = LocalDate.of(year, mon, day);
            DateTimeFormatter btf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return initDate.format(btf);
        }
    }

    public List<Meteo> dataVelocidadViento(List<Meteo> listaPuntoMuestreo) {
        return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_81_89")).collect(Collectors.toList());
    }
    public List<Meteo> dataDireccionViento(List<Meteo> listaPuntoMuestreo) {
        return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_82_89")).collect(Collectors.toList());
    }
    public List<Meteo> dataTemperatura(List<Meteo> listaPuntoMuestreo) {
        return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_83_89")).collect(Collectors.toList());
    }
    public List<Meteo> dataHumedadRelativa(List<Meteo> listaPuntoMuestreo) {
        return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_86_89")).collect(Collectors.toList());
    }
    public List<Meteo> dataPresionAtmosferica(List<Meteo> listaPuntoMuestreo) {
        return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_87_89")).collect(Collectors.toList());
    }
    public List<Meteo> dataRadiacionSolar(List<Meteo> listaPuntoMuestreo) {
        return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_88_89")).collect(Collectors.toList());
    }
    public List<Meteo> dataPrecipitacion(List<Meteo> listaPuntoMuestreo) {
        return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_89_89")).collect(Collectors.toList());
    }

    public List<Double> dataAverage(List<Meteo> lista) {
        if (lista.isEmpty()) {
            System.err.println("** Error: Lista de medias vacia **");
        }
        List<Double> values = new ArrayList<>();
        lista.forEach(m -> {

            double sum = 0;
            int notN = 0;


            if (m.getV01().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH01());
                notN += 1;
                values.add(Double.parseDouble(m.getH01()));
            }
            if (m.getV02().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH02());
                notN += 1;
                values.add(Double.parseDouble(m.getH02()));

            }
            if (m.getV03().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH03());
                notN += 1;
                values.add(Double.parseDouble(m.getH03()));

            }
            if (m.getV04().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH04());
                notN += 1;
                values.add(Double.parseDouble(m.getH04()));

            }
            if (m.getV05().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH05());
                notN += 1;
                values.add(Double.parseDouble(m.getH05()));

            }
            if (m.getV06().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH06());
                notN += 1;
                values.add(Double.parseDouble(m.getH06()));

            }
            if (m.getV07().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH07());
                notN += 1;
                values.add(Double.parseDouble(m.getH07()));

            }
            if (m.getV08().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH08());
                notN += 1;
                values.add(Double.parseDouble(m.getH08()));

            }
            if (m.getV09().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH09());
                notN += 1;
                values.add(Double.parseDouble(m.getH09()));

            }
            if (m.getV10().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH10());
                notN += 1;
                values.add(Double.parseDouble(m.getH10()));

            }
            if (m.getV11().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH11());
                notN += 1;
                values.add(Double.parseDouble(m.getH11()));

            }
            if (m.getV12().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH12());
                notN += 1;
                values.add(Double.parseDouble(m.getH12()));

            }
            if (m.getV13().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH13());
                notN += 1;
                values.add(Double.parseDouble(m.getH13()));

            }
            if (m.getV14().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH14());
                notN += 1;
                values.add(Double.parseDouble(m.getH14()));

            }
            if (m.getV15().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH15());
                notN += 1;
                values.add(Double.parseDouble(m.getH15()));

            }
            if (m.getV16().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH16());
                notN += 1;
                values.add(Double.parseDouble(m.getH16()));

            }
            if (m.getV17().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH17());
                notN += 1;
                values.add(Double.parseDouble(m.getH17()));

            }
            if (m.getV18().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH18());
                notN += 1;
                values.add(Double.parseDouble(m.getH18()));
            }
            if (m.getV19().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH19());
                notN += 1;
                values.add(Double.parseDouble(m.getH19()));

            }
            if (m.getV20().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH20());
                notN += 1;
                values.add(Double.parseDouble(m.getH20()));

            }
            if (m.getV21().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH21());
                notN += 1;
                values.add(Double.parseDouble(m.getH21()));

            }
            if (m.getV22().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH22());
                notN += 1;
                values.add(Double.parseDouble(m.getH22()));

            }
            if (m.getV23().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH23());
                notN += 1;
                values.add(Double.parseDouble(m.getH23()));

            }
            if (m.getV24().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH24());
                notN += 1;
                values.add(Double.parseDouble(m.getH24()));
            }
            sumaMediaDias += sum / notN;
        });

        Double min = values.stream().min(Comparator.comparing(v -> v)).get();
        Double max = values.stream().min(Comparator.comparing(v -> v)).get();

        return List.of(sumaMediaDias/lista.size(),min,max) ;
    }

}
