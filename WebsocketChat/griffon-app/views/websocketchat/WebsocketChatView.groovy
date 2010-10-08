package websocketchat
import net.miginfocom.swing.MigLayout
import org.jdesktop.swingx.decorator.HighlighterFactory

application(title: 'WebsocketChat',
  size: [600,500],
  //location: [50,50],
  //pack:true,
  locationByPlatform:true,
  layout:new MigLayout(),
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
    // add content here
	menuBar {
		menu('File') {
			menuItem(text:'Set Endpoint', actionPerformed:{})
			menuItem(text:'Login', actionPerformed:controller.login)
			menuItem(text:'Logout', actionPerformed:{})
			menuItem(text:"Exit",  actionPerformed:{System.exit(0)})
		}
	}
	scrollPane(id:'screenshot', constraints:'w 700px , h 100%')
	panel (layout:new MigLayout(), constraints:'w 200px, h 100%') {
		scrollPane(id:'scroll', constraints:'h 90%, w 200px, wrap'){
			jxlist(model:model.msgModel, highlighters:[HighlighterFactory.createSimpleStriping()])
		}
		panel (layout:new MigLayout(), constraints:'w 200px'){
				comboBox(id:'cbox', items:['Send Message', 'Send Question'], constraints:'wrap')
				textArea(id:'msgText', columns:15, lineWrap:true, constraints:'wrap')
				button(text:'Send', actionPerformed:{controller.sendMessage(msgText?.text)})
		}
	}
}
