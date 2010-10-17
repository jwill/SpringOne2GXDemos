package griffonstocktracker

import groovy.beans.Bindable
import ca.odell.glazedlists.*
import ca.odell.glazedlists.swing.*

class GriffonStockTrackerModel {
   // @Bindable String propName
   EventList stockEventList = new BasicEventList<Stock>()
   EventListModel stockModel = new EventListModel(stockEventList)
}
