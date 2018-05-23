package TestParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Integrator {
    enum Methods {
        Euler("Euler"),
        ModifyEuler("Modify-Euler"),
        RungeKutti1("Runge-Kutti-1"),
        RungeKutti2("Runge-Kutti-2"),
        RungeKutti3("Runge-Kutti-3"),
        RungeKutti4("Runge-Kutti-4");

        private String name;

        Methods(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    Map<String, String> equations = new HashMap<>();
    Map<String, String> begin_conditions = new HashMap<>();
    String integration_method;
    String integration_var_value;
    String integration_var_step_value;

    @Override
    public String toString() {
        return "Integrator{" +
                "\n\tequations=" + equations +
                ";\n\tbegin_conditions=" + begin_conditions +
                ";\n\tintegration_method=" + integration_method +
                ";\n\tintegration_var_value=" + integration_var_value +
                ";\n\tintegration_var_step_value=" + integration_var_step_value +
                "\n}";
    }

    Map<Double, Map<String, Double>> run() throws Exception {
        return euler();
    }

    Map<Double, Map<String, Double>> euler() throws Exception {
        Map<Double, Map<String, Double>> result = new HashMap<>();
        Map<String, String> params = new HashMap<>(begin_conditions);

        Map<String, Double> bufferResult = new HashMap<>();
        for (Entry entry: equations.entrySet()){
            String integration_var = ((String) entry.getKey()).replace("/dt", "");
            String init_value = params.get(integration_var);
            bufferResult.put((String)entry.getKey(), Double.parseDouble(init_value));
        }
        result.put((double) 0, bufferResult);

        for (double t: new Range(
                (double)1,
                Double.parseDouble(integration_var_value) + Double.parseDouble(integration_var_step_value),
                Double.parseDouble(integration_var_step_value)).toList()){

            bufferResult.clear();
            for (Entry entry: equations.entrySet()){
                double equation_value = MathSolver.solv((String) entry.getValue(), params);
                String integration_var = ((String) entry.getKey()).replace("/dt", "");
                equation_value = (Double.parseDouble(params.get(integration_var))) +
                        Double.parseDouble(integration_var_step_value) * equation_value;
                bufferResult.put((String) entry.getKey(), equation_value);
                params.put(integration_var, Double.toString(equation_value));
            }
            result.put(t, bufferResult);
        }

        return result;
    }

    Map<Double, Map<String, Double>> modify_euler() throws Exception{
        Map<Double, Map<String, Double>> result = new HashMap<>();
        Map<String, String> params = new HashMap<>(begin_conditions);

        Map<String, Double> bufferResult = new HashMap<>();
        for (Entry entry: equations.entrySet()){
            String integration_var = ((String) entry.getKey()).replace("/dt", "");
            String init_value = params.get(integration_var);
            bufferResult.put((String)entry.getKey(), Double.parseDouble(init_value));
        }
        result.put((double) 0, bufferResult);

        for (double t: new Range(
                (double)1,
                Double.parseDouble(integration_var_value) + Double.parseDouble(integration_var_step_value),
                Double.parseDouble(integration_var_step_value)).toList()){

            bufferResult.clear();
            for (Entry entry: equations.entrySet()){
                Map<String, String> params_temp = new HashMap<>(params);
                String integration_var = ((String) entry.getKey()).replace("/dt", "");

                // 1
                double equation_value_1 = MathSolver.solv((String) entry.getValue(), params_temp);
                double equation_value = (Double.parseDouble(params_temp.get(integration_var))) + equation_value_1;
                params_temp.put(integration_var, Double.toString(equation_value));
                // 2
                double equation_value_2 = MathSolver.solv((String) entry.getValue(), params_temp);
                // result value
                equation_value = Double.parseDouble(params.get(integration_var)) + 0.5 *
                        Double.parseDouble(integration_var_step_value) * (equation_value_1 + equation_value_2);
                bufferResult.put((String) entry.getKey(), equation_value);
                params.put(integration_var, Double.toString(equation_value));
            }
            result.put(t, bufferResult);
        }
        return result;
    }

    Map<Double, Map<String, Double>> runge_kutti() throws Exception{
        return runge_kutti(1);
    }

    Map<Double, Map<String, Double>> runge_kutti(int n) throws Exception{
        switch (n){
            case 1:
                return euler();
            case 2:
                return modify_euler();
            case 3:
                return runge_kutti_3();
            case 4:
                return runge_kutti_4();
        }

        throw new Exception("Неизвестная степень метода Рунге-Кутта. Укажите один из следующих:\n" +
                "Runge-Kutti-1; Runge-Kutti-2; Runge-Kutti-3; Runge-Kutti-4") {{
        }};
    }

    Map<Double, Map<String, Double>> runge_kutti_3() throws Exception {
        Map<Double, Map<String, Double>> result = new HashMap<>();
        Map<String, String> params = new HashMap<>(begin_conditions);

        Map<String, Double> bufferResult = new HashMap<>();
        for (Entry entry: equations.entrySet()){
            String integration_var = ((String) entry.getKey()).replace("/dt", "");
            String init_value = params.get(integration_var);
            bufferResult.put((String)entry.getKey(), Double.parseDouble(init_value));
        }
        result.put((double) 0, bufferResult);

        for (double t: new Range(
                (double)1,
                Double.parseDouble(integration_var_value) + Double.parseDouble(integration_var_step_value),
                Double.parseDouble(integration_var_step_value)).toList()) {

            bufferResult.clear();
            for (Entry entry : equations.entrySet()) {
                // pass
                // pass
                // pass
                // pass  Метод еще не реализован
                // pass
                // pass
                // pass
            }
            result.put(t, bufferResult);
        }

        return result;
    }

    Map<Double, Map<String, Double>> runge_kutti_4() throws Exception{
        Map<Double, Map<String, Double>> result = new HashMap<>();
        Map<String, String> params = new HashMap<>(begin_conditions);

        Map<String, Double> bufferResult = new HashMap<>();
        for (Entry entry: equations.entrySet()){
            String integration_var = ((String) entry.getKey()).replace("/dt", "");
            String init_value = params.get(integration_var);
            bufferResult.put((String)entry.getKey(), Double.parseDouble(init_value));
        }
        result.put((double) 0, bufferResult);

        for (double t: new Range(
                (double)1,
                Double.parseDouble(integration_var_value) + Double.parseDouble(integration_var_step_value),
                Double.parseDouble(integration_var_step_value)).toList()) {

            bufferResult.clear();
            for (Entry entry : equations.entrySet()) {
                Map<String, String> params_temp = new HashMap<>(params);
                String integration_var = ((String) entry.getKey()).replace("/dt", "");

                // 1
                double equation_value_1 = MathSolver.solv((String) entry.getValue(), params_temp);
                double equation_value = Double.parseDouble(params_temp.get(integration_var)) + 0.5 *
                        Double.parseDouble(integration_var_step_value) * equation_value_1;
                params_temp.put(integration_var, Double.toString(equation_value));

                // 2
                double equation_value_2 = MathSolver.solv((String) entry.getValue(), params_temp);
                equation_value = Double.parseDouble(params_temp.get(integration_var)) + 0.5 *
                        Double.parseDouble(integration_var_step_value) * equation_value_2;
                params_temp.put(integration_var, Double.toString(equation_value));

                // 3
                double equation_value_3 = MathSolver.solv((String) entry.getValue(), params_temp);
                equation_value = Double.parseDouble(params_temp.get(integration_var)) + equation_value_3;
                params_temp.put(integration_var, Double.toString(equation_value));

                // 4
                double equation_value_4 = MathSolver.solv((String) entry.getValue(), params_temp);
                equation_value = Double.parseDouble(params_temp.get(integration_var)) + equation_value_4;

                // result value
                double y_val = (1 / 6) * Double.parseDouble(integration_var_step_value) *
                        (equation_value_1 + 2 * equation_value_2 + 2 * equation_value_3 + equation_value_4);
                equation_value = Double.parseDouble(params_temp.get(integration_var)) + y_val;

                bufferResult.put((String) entry.getKey(), equation_value);
                params.put(integration_var, Double.toString(equation_value));
            }
            result.put(t, bufferResult);
        }

        return result;
    }
}
