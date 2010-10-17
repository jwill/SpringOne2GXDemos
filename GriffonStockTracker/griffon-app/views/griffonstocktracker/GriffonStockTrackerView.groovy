package griffonstocktracker
import net.miginfocom.swing.MigLayout

application(title: 'GriffonStockTracker',
  size: [400,400],
  pack: true,
  layout: new MigLayout(),
  //location: [50,50],
  locationByPlatform:true,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
	panel (layout:new MigLayout()){
	jxtable(id:'symbolsTable', constraints:'w 160px')
	panel(layout:new MigLayout(), constraints:'w !') {
		label('Value:')
		label(id:'txtCompanyName')
		label('Change:', constraints:'newline')
		label(id:'txtChange')
		label('Open:', constraints:'newline')
		label(id:'txtOpen')
		label('High:', constraints:'newline')
		label(id:'txtHigh')
		label('Low:', constraints:'newline')
		label(id:'txtLow')
		label('Volume:', constraints:'newline')
		label(id:'txtVolume')
	}
	}
    
    panel(layout:new MigLayout(), constraints:'newline') {
		label("Symbol")
		textField(id:"stock", columns:10)
		button(icon:silkIcon(icon:'add'))
		button(icon:silkIcon(icon:'delete'))
    }
    jxstatusBar(){
		label(id: 'statusLabel', 'test')
    }
}

