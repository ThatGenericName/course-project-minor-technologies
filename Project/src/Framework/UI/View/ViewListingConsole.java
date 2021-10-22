package Framework.UI.View;

import Entities.Listing.Listing;
import Framework.UI.View.Interfaces.IViewListing;
import UseCase.DisplayData.IDisplayData;
import UseCase.DisplayData.ListingDisplayData;

public class ViewListingConsole implements IViewListing {
    @Override
    public void displayListing(IDisplayData displayData) {
        if (displayData instanceof ListingDisplayData){

        }
    }
}
