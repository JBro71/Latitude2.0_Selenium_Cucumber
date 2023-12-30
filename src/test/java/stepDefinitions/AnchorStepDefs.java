package stepDefinitions;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import com.google.gson.Gson;
import io.cucumber.java.en.*;
import pageObjects.DesktopAnchorPanels;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;

public class AnchorStepDefs {
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions stepDefCF;
	DesktopAnchorPanels desktopAnchorPanels;
	HashMap<String,String> payloadMap;


	
	public AnchorStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		stepDefCF = context.getStepDefCommonFunctions();
		desktopAnchorPanels = context.getDesktopAnchorPanels();
	}
	

	@Given("the {string} anchor panel will display a {string} value of {string}")
	public void the_anchor_panel_will_display_a_value_of(String panelName, String label, String value) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		HashMap<String,String> dataMap = new HashMap<String,String>();
		dataMap.put(label, value);
		//convert any variables to real values
		dataMap = stepDefCF.processVariables(dataMap);
		pageUtils.openAnchorPanel();
		String anchorPanelValue =  desktopAnchorPanels.queryAnchorPanels(panelName, label);
		Assert.assertEquals("Anchor panel: " + panelName + "/" + label, dataMap.get(label), anchorPanelValue);
		pageUtils.closeAnchorPanel();
	}
	
	@Then("the anchor panels will display the following values")
	public void the_anchor_panels_will_display_the_following_values(io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		HashMap<String,String> dataMap = new HashMap<String,String>();
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> row : dataList) {
			dataMap.put(row.get(1), row.get(2));
		}
		//convert any variables to real values
		dataMap = stepDefCF.processVariables(dataMap);
		pageUtils.openAnchorPanel();
		for (List<String> row : dataList) {
			String anchorPanelValue =  desktopAnchorPanels.queryAnchorPanels(row.get(0), row.get(1));
			Assert.assertEquals("Anchor panel: " + row.get(0) + "/" + row.get(1),dataMap.get(row.get(1)), anchorPanelValue);
		}
		pageUtils.closeAnchorPanel();
	}
	

	

	
	
}
