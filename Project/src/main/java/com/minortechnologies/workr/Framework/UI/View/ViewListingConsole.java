package com.minortechnologies.workr.Framework.UI.View;

import com.minortechnologies.workr.Framework.UI.View.Interfaces.IViewListing;
import com.minortechnologies.workr.UseCase.DisplayData.IDisplayData;
import com.minortechnologies.workr.UseCase.DisplayData.ListingDisplayData;

public class ViewListingConsole implements IViewListing {
    @Override
    public void displayListing(IDisplayData displayData) {
        if (displayData instanceof ListingDisplayData){

        }
    }
}
