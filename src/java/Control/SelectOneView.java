package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.Pais;
import Persistence.Conexao;
import Persistence.Dao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;

/**
 *
 * @author gabrielferreira
 */
@Named(value = "selectOneView")
@SessionScoped
@ManagedBean
public class SelectOneView implements Serializable {

    private String tipo;
    private ArrayList<Pais> paises;
    private BarChartModel barModel;
    private PieChartModel pieModel1;
     private BubbleChartModel bubbleModel2;

    public void init() {
        createBarModels();
        createPieModels();
        createBubbleModels();
    }

    //---------------------------------Métodos da tabela--------------------------------------------
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String pegarTipo() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Conexao con = new Conexao();
        Dao dao = new Dao(con);
        paises = dao.list();
        this.init();
        return tipo;
    }

    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPaises(ArrayList<Pais> paises) {
        this.paises = paises;
    }

      //---------------------------------Métodos das barras--------------------------------------------
    public BarChartModel getBarModel() {
        return barModel;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    private void createBarModels(){
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Valores");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Países");
        yAxis.setMin(0);
        
        switch(tipo){
            case "densidade_demografica" : yAxis.setMax(20000.0); break;
                    case "idh" : yAxis.setMax(1.0); break;
                        case "pib" : yAxis.setMax(20000000.0); break;
                            case "populacao" : yAxis.setMax(8000000000.0); break;
                                case "area" : yAxis.setMax(20000000.0); break;
                                default : yAxis.setMax(0.0);
            
        
        }
    }

    private BarChartModel initBarModel(){
        BarChartModel model = new BarChartModel();

        for (int i = 0; i < this.paises.size(); i++) {
            ChartSeries pais = new ChartSeries();
            pais.setLabel(paises.get(i).nome);
            pais.set(paises.get(i).getNome(), paises.get(i).getAtributo(tipo));
            model.addSeries(pais);
        }

        return model;
    }

    private void createPieModels() {
        createPieModel1();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);

        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }

    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

      //---------------------------------Métodos das Bolhas--------------------------------------------
    public BubbleChartModel getBubbleModel2() {
        return bubbleModel2;
    }
    
     private void createBubbleModels(){     
        bubbleModel2 = initBubbleModel();
        bubbleModel2.setTitle("Representação por Bolhas");
        bubbleModel2.setShadow(true);
        bubbleModel2.setBubbleGradients(true);
        bubbleModel2.setBubbleAlpha(0.8);
        bubbleModel2.getAxis(AxisType.X).setTickAngle(-50);
        Axis yAxis = bubbleModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        
        switch(tipo){
            case "densidade_demografica" : yAxis.setMax(20000.0); break;
                    case "idh" : yAxis.setMax(1.0); break;
                        case "pib" : yAxis.setMax(20000000.0); break;
                            case "populacao" : yAxis.setMax(8000000000.0); break;
                                case "area" : yAxis.setMax(20000000.0); break;
                                default : yAxis.setMax(0.0);
            
        
        }
        
        yAxis.setTickAngle(50);
    }
     
    private BubbleChartModel initBubbleModel(){
        BubbleChartModel model = new BubbleChartModel();
         
       
        for(int i = 0; i < paises.size(); i++){
            model.add (new BubbleChartSeries(paises.get(i).nome, i+1, (int) (paises.get(i).getAtributo(tipo)/1), (int) (paises.get(i).getAtributo(tipo)/2)));
        }
        return model;
    }
}
