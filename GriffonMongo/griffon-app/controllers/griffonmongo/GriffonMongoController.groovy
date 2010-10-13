package griffonmongo

class GriffonMongoController {
    // these will be injected by Griffon
    def model
    def view

    void mvcGroupInit(Map args) {
        // this method is called after model and view are injected
    }
    
    def addConnection = { name, host, port ->
		// create entry for item
		// push to json array and save
    }
    
    def removeConnection = { name ->
    
    }
    
    def createDatabase = { ->
    
    }
    
    def createCollection = { ->
    
    }
    
    def dropDatabase = { ->
    
    }
    
    def dropCollection = { ->
    
    }
    
    def runScript = { text ->
    
    }

    /*
    def action = { evt = null ->
    }
    */
}
