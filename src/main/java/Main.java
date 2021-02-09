import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        List<Square> listaObiektow = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            listaObiektow.add(new Square(rand.nextInt(50)+1));
        }
        System.out.println("######################################");
        System.out.println("A)Figury o większym polu niż obwód:");
        List<Square> poleWiekszeOdObwodu = listaObiektow.stream()
                .filter(kwadrat -> kwadrat.getArea() > kwadrat.getPerimeter())
                .collect(Collectors.toList());
        System.out.println(poleWiekszeOdObwodu);

        System.out.println("######################################");
        System.out.println("B)Rosnąco figury według obwodu:");
        List<Square> obwodRosnaco = listaObiektow.stream()
                .sorted(Comparator.comparingDouble(kwadrat -> kwadrat.getPerimeter()))
                .collect(Collectors.toList());
        System.out.println(obwodRosnaco);

        System.out.println("######################################");
        System.out.println("C)Średnia obwodów szystkich figur:");
        DoubleSummaryStatistics sredniaObwodow = listaObiektow.stream()
                .mapToDouble(kwadrat -> kwadrat.getPerimeter())
                .summaryStatistics();
        System.out.println(sredniaObwodow.getAverage());

        System.out.println("######################################");
        System.out.println("D)Posortowane malejąco figury o mniejszym polu niż średnia wszystkich figur:");
        DoubleSummaryStatistics sredniaPol = listaObiektow.stream()
                .mapToDouble(kwadrat -> kwadrat.getArea())
                .summaryStatistics();
        List<Square> poleSortowaneMalejaco = listaObiektow.stream()
                .filter(kwadrat -> kwadrat.getArea() < sredniaPol.getAverage())
                .sorted(Comparator.comparingDouble(Square::getArea).reversed())
                .collect(Collectors.toList());
        System.out.println(poleSortowaneMalejaco);

        System.out.println("######################################");
        System.out.println("E)Suma pól wszystkich figur:");
        DoubleSummaryStatistics sumaPol = listaObiektow.stream()
                .mapToDouble(kwadrat -> kwadrat.getArea())
                .summaryStatistics();
        System.out.println(sumaPol.getSum());
        
        System.out.println("######################################");
        System.out.println("Mapa wszystkich obiektów");
        Map<Square, Double> mapaObiektow = listaObiektow.stream()
                .collect(Collectors.toMap(kwadrat -> kwadrat, kwadrat -> kwadrat.getArea()));
        System.out.println(mapaObiektow);
    }
}
