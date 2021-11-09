package service;

import model.pojos_CSV_To_XML.Meteo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MeteoUtil {

    private List<Meteo> data;
    private Double sumaMediaDias = 0.0;

    public MeteoUtil(List<Meteo> data) {
        this.data = data;
    }

    /**
     * Metodo que devuelve una lista de objetos de la clase Meteo la cual esta filtrada por que contenga el Punto de Muestreo
     *
     * @param punto_muestreo String que contendra un punto de muestreo
     * @return Lista que contiene el punto de muestreo dado
     */
    public List<Meteo> getDataPuntoMuestreo(String punto_muestreo) {

        return data.stream().filter(p -> p.getPuntoMuestreo().contains(punto_muestreo)).collect(Collectors.toList());
    }

    /**
     * Metodo que devuelve una lista con los Puntos de muestreo solamente, viene muy bien para filtrar
     * @return Lista con todos los puntos de muestreo sin repetir y ordenados
     */
    public List<String> getPuntosMuestreo() {

        return data.stream().map(Meteo::getPuntoMuestreo).sorted().distinct().collect(Collectors.toList());
    }

    /**
     * Metodo que filtra y busca la fecha Inicio
     * @param listaPuntoMuestreo  Lista puntoMuestreo
     * @return Fecha inicio en un formato personalizado
     */
    public String getDataFechaIncio(List<Meteo> listaPuntoMuestreo) {
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

    /**
     * Metodo que filtra y busca la fecha Fin
     * @param listaPuntoMuestreo Lista puntoMuestreo
     * @return Fecha fin en un formato personalizado
     */
    public String getDataFechaFin(List<Meteo> listaPuntoMuestreo) {
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

    /**
     * Metodo para filtrar en los puntos de muestreo para saber que tipo de medida es
     * @param puntoMuestreo  String puntoMuestreo
     * @param listaPuntoMuestreo Lista puntoMuestreo
     * @return lista filtrada por un tipo concreto de medida / lista vacia en caso que no encuentre nada
     */
    public List<Meteo> getListMeasure(String puntoMuestreo, List<Meteo> listaPuntoMuestreo) {

        List<Meteo> emptyList = new ArrayList<>();

        if (puntoMuestreo.contains("_81_89")) {
            return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_81_89")).collect(Collectors.toList());
        } else if (puntoMuestreo.contains("_82_89")) {
            return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_82_89")).collect(Collectors.toList());
        } else if (puntoMuestreo.contains("_83_89")) {
            return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_83_89")).collect(Collectors.toList());
        } else if (puntoMuestreo.contains("_86_89")) {
            return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_86_89")).collect(Collectors.toList());
        } else if (puntoMuestreo.contains("_87_89")) {
            return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_87_89")).collect(Collectors.toList());
        } else if (puntoMuestreo.contains("_88_89")) {
            return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_88_89")).collect(Collectors.toList());
        } else if (puntoMuestreo.contains("_89_89")) {
            return listaPuntoMuestreo.stream().filter(f -> f.getPuntoMuestreo().contains("_89_89")).collect(Collectors.toList());
        }
        return emptyList;
    }

    /**
     * Metodo que calcula los maximos, minimos y medias obviando los valores nulos o no validos
     * @param lista Lista de objetos de la clase Meteo
     * @return Lista de double con la media en la primera posicion, min segunda y max tercera
     */
    public List<Double> getDataAverageMinMax(List<Meteo> lista) {
        if (lista.isEmpty()) {
            System.err.println("** Error: Lista de medias vacia **");
        }
        List<Double> values = new ArrayList<>();
        lista.forEach(m -> {

            double sum = 0;
            int notN = 0;


            if (m.getV01().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH01().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH01().replace(",", ".")));
            }
            if (m.getV02().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH02().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH02().replace(",", ".")));

            }
            if (m.getV03().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH03().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH03().replace(",", ".")));

            }
            if (m.getV04().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH04().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH04().replace(",", ".")));

            }
            if (m.getV05().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH05().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH05().replace(",", ".")));

            }
            if (m.getV06().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH06().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH06().replace(",", ".")));

            }
            if (m.getV07().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH07().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH07().replace(",", ".")));

            }
            if (m.getV08().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH08().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH08().replace(",", ".")));

            }
            if (m.getV09().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH09().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH09().replace(",", ".")));

            }
            if (m.getV10().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH10().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH10().replace(",", ".")));

            }
            if (m.getV11().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH11().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH11().replace(",", ".")));

            }
            if (m.getV12().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH12().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH12().replace(",", ".")));

            }
            if (m.getV13().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH13().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH13().replace(",", ".")));

            }
            if (m.getV14().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH14().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH14().replace(",", ".")));

            }
            if (m.getV15().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH15().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH15().replace(",", ".")));

            }
            if (m.getV16().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH16().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH16().replace(",", ".")));

            }
            if (m.getV17().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH17().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH17().replace(",", ".")));

            }
            if (m.getV18().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH18().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH18().replace(",", ".")));
            }
            if (m.getV19().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH19().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH19().replace(",", ".")));

            }
            if (m.getV20().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH20().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH20().replace(",", ".")));

            }
            if (m.getV21().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH21().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH21().replace(",", ".")));

            }
            if (m.getV22().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH22().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH22().replace(",", ".")));

            }
            if (m.getV23().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH23().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH23().replace(",", ".")));

            }
            if (m.getV24().equalsIgnoreCase("V")) {
                sum += Double.parseDouble(m.getH24().replace(",", "."));
                notN += 1;
                values.add(Double.parseDouble(m.getH24().replace(",", ".")));
            }
            sumaMediaDias += sum / notN;
        });

        Double min = values.stream().min(Comparator.comparing(v -> v)).get();
        Double max = values.stream().max(Comparator.comparing(v -> v)).get();


        return List.of(sumaMediaDias / lista.size(), min, max);
    }




}
