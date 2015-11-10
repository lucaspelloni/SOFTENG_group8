package com.google.gwt.sample.SE_group9.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.DateFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.FloatFieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SE_group9 implements EntryPoint{

	/**
	 * This is the entry point method.
	 */
	
	/*stores the path where the db "Movies_80000" is saved (war) */
	public final static String MOVIES_PATH = "/excelFiles/movies_80000.tsv";

	public static int importedNumberOfLines = 0;
	public static int importedNumberOfColumns = 0; 
	
	/*Array which takes as input the data set in the server class*/
	public static String [][] twoDimArrayImportedDataSet;

	/*async object used for the server side import of the data*/
	public GreetingServiceAsync newData = GWT.create(GreetingService.class);
	/*async object used for the server side getNumberOfLines*/
	//public GreetingServiceAsync importedNumberOfLines = GWT.create(GreetingService.class);
	
	/*async object used for the server side getNumberOfColumns*/
	//public GreetingServiceAsync importedNumberOfColumns = GWT.create(GreetingService.class);
	
	  private VerticalPanel mainPanel = new VerticalPanel();
	  private FlexTable stocksFlexTable = new FlexTable();
	  private HorizontalPanel addPanel = new HorizontalPanel();
	  private TextBox newSymbolTextBox = new TextBox();
	  private Button addStockButton = new Button("Add");
	  private Label lastUpdatedLabel = new Label();
	  public VerticalPanel panel1 = new VerticalPanel();
	  public Label errorLabel = new Label ();
	  public Label errorLabel2 = new Label ("ERROR2");
	  public Label successfullLabel = new Label ("OK");
	  public Label ciao2 = new Label ("NR OF LINES");
	  public Label ciao = new Label ("NR OF COL");

	public void onModuleLoad() {
/*
		final Button laender = new Button("CLICK ME");
		final Label lb = new Label ("CULO");

		laender.setText("CLICK ME");
	    laender.addClickHandler(new ClickHandler() {
	    	
	    	 /* by clicking on the button, clear the two scroll panels and load the panel 1 content with countries
	    	 */
	    	 /*
		      @Override
		      public void onClick(ClickEvent event) {

				importNumberOfLines (MOVIES_PATH);
				importNumberOfColums (MOVIES_PATH);
		    	importTwoDimArrayToClient (MOVIES_PATH);
	
		      }
		    });
	    	
	    
	    RootPanel.get("test").add(laender);
	    RootPanel.get("test").add(lb);
	    RootPanel.get("test").add(errorLabel);
	    RootPanel.get("test").add(errorLabel2);
	    RootPanel.get("test").add(successfullLabel);
	    RootPanel.get("test").add(ciao);
	    RootPanel.get("test").add(ciao2);
	    
	    
	    
	    
	    */
		
		
		    Panel panel = new Panel();  
	        panel.setBorder(false);  
	        panel.setPaddings(15);  
	  
	        RecordDef recordDef = new RecordDef(  
	                new FieldDef[]{  
	                        new StringFieldDef("company"),  
	                        new FloatFieldDef("price"),  
	                        new FloatFieldDef("change"),  
	                        new FloatFieldDef("pctChange"),  
	                        new DateFieldDef("lastChanged", "n/j h:ia"),  
	                        new StringFieldDef("symbol"),  
	                        new StringFieldDef("industry")  
	                }  
	        );  
	  
	        final GridPanel grid = new GridPanel();  
	  
	        Object[][] data = getCompanyData();  
	        MemoryProxy proxy = new MemoryProxy(data);  
	  
	        ArrayReader reader = new ArrayReader(recordDef);  
	        Store store = new Store(proxy, reader);  
	        store.load();  
	        grid.setStore(store);  
	  
	  
	        ColumnConfig[] columns = new ColumnConfig[]{  
	                //column ID is company which is later used in setAutoExpandColumn  
	                new ColumnConfig("Company", "company", 160, true, null, "company"),  
	                new ColumnConfig("Price", "price", 35),  
	                new ColumnConfig("Change", "change", 45),  
	                new ColumnConfig("% Change", "pctChange", 65),  
	                new ColumnConfig("Last Updated", "lastChanged", 65),  
	                new ColumnConfig("Industry", "industry", 60, true)  
	        };  
	  
	        ColumnModel columnModel = new ColumnModel(columns);  
	        grid.setColumnModel(columnModel);  
	  
	        grid.setFrame(true);  
	        grid.setStripeRows(true);  
	        grid.setAutoExpandColumn("company");  
	  
	        grid.setHeight(350);  
	        grid.setWidth(600);  
	        grid.setTitle("Array Grid");  
	  
	        Toolbar bottomToolbar = new Toolbar();  
	        bottomToolbar.addFill();  
	        bottomToolbar.addButton(new ToolbarButton("Clear Sort", new ButtonListenerAdapter() {  
	            public void onClick(Button button, EventObject e) {  
	                grid.clearSortState(true);  
	            }  
	        }));  
	        grid.setBottomToolbar(bottomToolbar);  
	  
	        panel.add(grid);  
	  
	        RootPanel.get().add(panel);  
	    }  
	  
	    private Object[][] getCompanyData() {  
	        return new Object[][]{  
	                new Object[]{"3m Co", new Double(71.72), new Double(0.02),  
	                        new Double(0.03), "9/1 12:00am", "MMM", "Manufacturing"},  
	                new Object[]{"Alcoa Inc", new Double(29.01), new Double(0.42),  
	                        new Double(1.47), "9/1 12:00am", "AA", "Manufacturing"},  
	                new Object[]{"Altria Group Inc", new Double(83.81), new Double(0.28),  
	                        new Double(0.34), "9/1 12:00am", "MO", "Manufacturing"},  
	                new Object[]{"American Express Company", new Double(52.55), new Double(0.01),  
	                        new Double(0.02), "9/1 12:00am", "AXP", "Finance"},  
	                new Object[]{"American International Group, Inc.", new Double(64.13), new Double(0.31),  
	                        new Double(0.49), "9/1 12:00am", "AIG", "Services"},  
	                new Object[]{"AT&T Inc.", new Double(31.61), new Double(-0.48),  
	                        new Double(-1.54), "9/1 12:00am", "T", "Services"},  
	                new Object[]{"Boeing Co.", new Double(75.43), new Double(0.53),  
	                        new Double(0.71), "9/1 12:00am", "BA", "Manufacturing"},  
	                new Object[]{"Caterpillar Inc.", new Double(67.27), new Double(0.92),  
	                        new Double(1.39), "9/1 12:00am", "CAT", "Services"},  
	                new Object[]{"Citigroup, Inc.", new Double(49.37), new Double(0.02),  
	                        new Double(0.04), "9/1 12:00am", "C", "Finance"},  
	                new Object[]{"E.I. du Pont de Nemours and Company", new Double(40.48), new Double(0.51),  
	                        new Double(1.28), "9/1 12:00am", "DD", "Manufacturing"}  
	        };  
	    }  
			
	
	
	
	
	
	
	
	
	
	/** calls the server side method to import the two dimensional string arrays
	 * @param path, the path of the MOVIES_80000.tsv file
	 */
	public void importTwoDimArrayToClient (String path) {
		if (newData == null) {
	    	newData = GWT.create(GreetingService.class);
	    }
		
		AsyncCallback <String[][]> callback = new AsyncCallback <String[][]> () {

			@Override
			public void onFailure(Throwable caught) {
				errorLabel.setText("Error in the import of the data set");
			}

			@Override
			public void onSuccess(String[][] result) {
				twoDimArrayImportedDataSet = new String [importedNumberOfLines][importedNumberOfColumns];

				twoDimArrayImportedDataSet = result; 
				errorLabel.setText("Number Of Lines: "+importedNumberOfLines);
				errorLabel2.setText("Number Of Columns: "+importedNumberOfColumns);
				
				
			}
			
		};
		
		newData.importData(path, callback);
		
	}
	
		
	/** calls the server side method to import the number of lines in
	 * the two dimensional array
	 * @param path, the path of the MOVIES_80000.tsv file
	 */
	public void importNumberOfLines (String path) {
		 // Initialize the service proxy.
	    if (newData == null) {
	    	newData = GWT.create(GreetingService.class);
	    }
	    
	    AsyncCallback <Integer> callback = new AsyncCallback <Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Integer result) {
				importedNumberOfLines = result;
				
			}
	    
	    };
	    
	    newData.getNumberOfLines(path, callback);
	}
	
	/** calls the server side method to import the number of columns in
	 * the two dimensional array
	 * @param path, the path of the MOVIES_80000.tsv file
	 */
	public void importNumberOfColums (String path) {
		 // Initialize the service proxy.
	    if (newData == null) {
	    	newData = GWT.create(GreetingService.class);
	    }
	    
	    AsyncCallback <Integer> callback = new AsyncCallback <Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Integer result) {
				importedNumberOfColumns = result; 
				
			}
	    	
	    	
	    	
	    };
	    
	    newData.getNumberOfColumns(path, callback);
	    
	}
	
	

	
	
	
	
	
}
	
