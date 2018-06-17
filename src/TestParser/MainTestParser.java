package TestParser;

import FileHelper.FileHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainTestParser extends Application {

    static String text = "Programm: DiffSolv1.0\n\n" +
            "Equations:\n" +
            "Susc/dt = -A * Susc * Sick;\n" +
            "Sick/dt = A * Susc * Sick - (B + C) * Sick;\n" +
            "Cured/dt = B * Sick;\n\n" +
            "BeginConditions:\n" +
            "Susc = 620;\n" +
            "Sick = 10;\n" +
            "Cured = 70;\n" +
            "A = 0.001;\n" +
            "B = 0.07;\n" +
            "C = 0.01;\n\n" +
            "IntegrationConditions:\n" +
            "method = Euler;\n" +
            "t = 50;\n" +
            "dt = 0.5;";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Чтение БНФ с файла
            ConsoleProxy.print("Запуск чтения файла");

            FileHelper fileHelper = new FileHelper("bnf.txt");
            text = fileHelper.read();

            ConsoleProxy.print("Файл прочитан успешно");

            ConsoleProxy.print("Запуск парсера БНФ");
            // Парсим код
            Parser parser = new Parser();
            parser.parse(text);
            ConsoleProxy.print("БНФ был распарсен успешно");

            ConsoleProxy.print("Запуск подготовки данных для графика");
            // Готовим данные для графика
            Map<Double, Map<String, Double>> data = parser.integrator.run();
            Map<String, Map<Double, Double>> data_clear = new HashMap<>();


            data.forEach((key, data_item) -> {
                data_item.forEach((var, value) -> {
                    if (!data_clear.containsKey(var))
                        data_clear.put(var, new HashMap<>());
                    data_clear.get(var).put(key, value);
                });
            });
            ConsoleProxy.print("Данные для графика подготовлены");


            StringBuilder resultDeff = new StringBuilder();
            data_clear.forEach((key, data_item) -> {
                resultDeff.append(
                        String.format(
                                "\n\t%s = %f",
                                key,
                                data_item.values().stream().mapToDouble(i -> i).sum() *
                                        Double.parseDouble(parser.integrator.integration_var_step_value)
                        )
                );
            });

            ConsoleProxy.print("\nРезультат обработки:");

            ConsoleProxy.print(resultDeff.toString());

            ConsoleProxy.print("\n\tМетод интегрирования: " + parser.integrator.integration_method);
            ConsoleProxy.print("\tШаг интегрирования: " + parser.integrator.integration_var_step_value);
            ConsoleProxy.print("\tИнтервал интегрирования: " + parser.integrator.integration_var_value + "\n");

            ConsoleProxy.print("Сборка Line Chart Application");
            // Application Line Chart
            primaryStage.setTitle("Line Chart");

            NumberAxis xAxis = new NumberAxis();
            NumberAxis yAxis = new NumberAxis();

            LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setCreateSymbols(false);

            primaryStage.setScene(new Scene(lineChart, 900, 400));

            data_clear.forEach((key, data_item) -> {
                XYChart.Series series = new XYChart.Series();
                series.setName(key);
                data_item.forEach((x, y) -> {
                    series.getData().add(new XYChart.Data(x, y));
                });
                lineChart.getData().add(series);
            });
            ConsoleProxy.print("Сборка Line Chart Application завершина успешно");

            ConsoleProxy.print("Запуск Line Chart Application");
            primaryStage.show();

        } catch (ParsingException ex) {
            ConsoleProxy.print(ex, text); // Дерьмо
//            primaryStage.close(); // не может возникнуть, как не крути
            primaryStage.show();
            primaryStage.close();
        } catch (Exception ex) {
            ConsoleProxy.print(ex);
            primaryStage.show();
            primaryStage.close();
        }
    }
}
