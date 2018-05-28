package TestParser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LineChartApplication extends Application {
    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart");

        //defining the axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");

        //creating the chart
        LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        Scene scene = new Scene(lineChart,800,600);
//        lineChart.getData().add(series);

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("My portfolio2");

        //populating the series with data
        series2.getData().add(new XYChart.Data(1.5, 23));
        series2.getData().add(new XYChart.Data(2.5, 14));
        series2.getData().add(new XYChart.Data(3.5, 15));
        series2.getData().add(new XYChart.Data(4.5, 24));
        series2.getData().add(new XYChart.Data(5.5, 34));
        series2.getData().add(new XYChart.Data(6.5, 36));
        series2.getData().add(new XYChart.Data(7.5, 22));
        series2.getData().add(new XYChart.Data(8.5, 45));
        series2.getData().add(new XYChart.Data(9.5, 43));
        series2.getData().add(new XYChart.Data(10.5, 17));
        series2.getData().add(new XYChart.Data(11.5, 29));
        series2.getData().add(new XYChart.Data(12.5, 25));

        ArrayList<XYChart.Series> example = new ArrayList<>(){{
            add(series);
            add(series2);
        }};

        XYChart.Series[] stockArr = new  XYChart.Series[example.size()];
        stockArr = example.toArray(stockArr);

//        lineChart.getData().add(series2);
        lineChart.getData().addAll(stockArr);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
