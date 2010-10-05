root {
    'groovy.swing.SwingBuilder' {
        controller = ['Threading']
        view = '*'
    }
    'griffon.app.ApplicationBuilder' {
        view = '*'
    }
}
root.'TangoiconsGriffonAddon'.addon=true

root.'MiglayoutGriffonAddon'.addon=true

root.'LookandfeelGriffonAddon'.addon=true

root.'griffon.builder.trident.TridentBuilder'.view = '*'

root.'LookandfeelSubstanceGriffonAddon'.addon=true

jx {
    'groovy.swing.SwingXBuilder' {
        view = '*'
    }
}
