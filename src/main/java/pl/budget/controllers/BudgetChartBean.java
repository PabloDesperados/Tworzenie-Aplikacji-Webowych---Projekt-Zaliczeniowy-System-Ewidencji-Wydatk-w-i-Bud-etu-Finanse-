
package pl.budget.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;

import pl.budget.common.BudgetCategory;

/**
 *
 * @author pawel
 */
@Named(value = "budgetChartBean")
@SessionScoped
public class BudgetChartBean implements Serializable {

    private List<BudgetCategory> categoriesList = new ArrayList<>();
    private LineChartModel lineModel;
    private BarChartModel barModel;
    /**
     * Creates a new instance of BudgetChartBean
     */
    public BudgetChartBean() {
    }
    
    @PostConstruct
    public void init() {
        categoriesList.add(new BudgetCategory(1, "Mieszkanie i media", 2400.0, false));
        categoriesList.add(new BudgetCategory(2, "Zywnosc", 1350.0, false));
        categoriesList.add(new BudgetCategory(3, "Transport i paliwo", 650.0, false));
        categoriesList.add(new BudgetCategory(4, "Edukacja i kursy", 480.0, false));
        categoriesList.add(new BudgetCategory(5, "Rozrywka i sport", 390.0, false));

        createLineModel();
        createBarModel();
    }

    public String addCategory() {
        long newId = categoriesList.isEmpty() ? 1 : categoriesList.get(categoriesList.size() - 1).getId() + 1;
        categoriesList.add(new BudgetCategory(newId, "Nowa kategoria", 0.0, true));
        createLineModel();
        createBarModel();
        return "";
    }

    public String deleteCategory(BudgetCategory category) {
        categoriesList.remove(category);
        createLineModel();
        createBarModel();
        return "";
    }

    public String editCategory() {
        createLineModel();
        createBarModel();
        return "";
    }

    public void visibleChange(BudgetCategory item) {
        int idx = categoriesList.indexOf(item);
        if (idx >= 0) {
            categoriesList.set(idx, item);
        }
        createLineModel();
        createBarModel();
    }

    public void sortByName(String dir) {
        Comparator<BudgetCategory> comparator = (BudgetCategory a, BudgetCategory b) -> {
            if ("asc".equals(dir)) {
                return a.getCategoryName().compareToIgnoreCase(b.getCategoryName());
            } else {
                return b.getCategoryName().compareToIgnoreCase(a.getCategoryName());
            }
        };
        Collections.sort(categoriesList, comparator);
        createLineModel();
        createBarModel();
    }

    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();
        LineChartDataSet dataSet = new LineChartDataSet();

        List<Object> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (BudgetCategory cat : categoriesList) {
            values.add(cat.getAmount());
            labels.add(cat.getCategoryName());
        }

        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Wydatki wg kategorii (PLN)");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Trend wydatkow (wykres liniowy)");
        options.setTitle(title);
        lineModel.setOptions(options);
        lineModel.setData(data);
    }

    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Kwota w PLN");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (BudgetCategory cat : categoriesList) {
            values.add(cat.getAmount());
            labels.add(cat.getCategoryName());
        }
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.5)");
        bgColor.add("rgba(255, 159, 64, 0.5)");
        bgColor.add("rgba(255, 205, 86, 0.5)");
        bgColor.add("rgba(75, 192, 192, 0.5)");
        bgColor.add("rgba(54, 162, 235, 0.5)");
        bgColor.add("rgba(153, 102, 255, 0.5)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(2);

        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barModel.setData(data);

        BarChartOptions options = new BarChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Struktura budzetu (wykres slupkowy)");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        options.setLegend(legend);

        barModel.setOptions(options);
    }

    public List<BudgetCategory> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<BudgetCategory> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
}
