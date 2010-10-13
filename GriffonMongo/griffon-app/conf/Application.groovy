application {
    title = 'GriffonMongo'
    startupGroups = ['GriffonMongo']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    frameClass = 'org.jdesktop.swingx.JXFrame'
}
mvcGroups {
    // MVC Group for "GriffonMongo"
    'GriffonMongo' {
        model = 'griffonmongo.GriffonMongoModel'
        controller = 'griffonmongo.GriffonMongoController'
        view = 'griffonmongo.GriffonMongoView'
    }

}
