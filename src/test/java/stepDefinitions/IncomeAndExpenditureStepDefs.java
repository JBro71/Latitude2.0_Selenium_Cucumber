package stepDefinitions;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopIncomeAndExpenditure;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;



public class IncomeAndExpenditureStepDefs {
	
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions stepDefCF;
	DesktopIncomeAndExpenditure dIAndE;
	
	public IncomeAndExpenditureStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		stepDefCF = context.getStepDefCommonFunctions();
		dIAndE = context.getIncomeAndExpenditure();
	}
	
	@Then("I can add {string} Income and Expenditure figures")
	public void i_can_add_income_and_expenditure_figures(String status, io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String[]> dataMap = new HashMap<String,String[]>();	
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> itemList : dataList) {
			for (int i=0; i<5; i++) {if(itemList.get(i) == null) {itemList.set(i, "");}} // replace nulls with ""
			String key = itemList.get(0).toLowerCase()+ "/" + itemList.get(1).toLowerCase()+ "/" + itemList.get(2).toLowerCase();
			String []	value = 	{itemList.get(3),  itemList.get(4)};
			//put "other essential costs code here
			dataMap.put(key, value);
			}	
		
		dIAndE.incomeAndExpenditure(dataMap, status);
	}
	

}

