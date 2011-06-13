package com.bleedingwolf.ratpack

import groovy.text.SimpleTemplateEngine
import javax.servlet.http.HttpServletRequest


class TemplateRenderer {
    
    String templateRoot = null

    TemplateRenderer(tr) {
        templateRoot = tr
    }

    String render(templateName, context=[:]) {

        String text = ''
        String fullTemplateFilename = [templateRoot, templateName].join(File.separator)
        
        try {
            new File(fullTemplateFilename).eachLine { text += it + '\n' }
        }
        catch(java.io.FileNotFoundException origEx) {
            try {
                def resource = this.class.getResourceAsStream(templateName)
                if (!resource) {
                    throw new java.io.FileNotFoundException(templateName)
                }
                text += resource.text
            }
            catch(java.io.FileNotFoundException resEx) {
                def resource = this.class.getResourceAsStream('exception.html')
                text += resource.text
                context = [
                    title: 'Template Not Found',
                    message: 'Template Not Found',
                    metadata: [
                        'Template Name': templateName,
                    ],
                    stacktrace: ""
                ]
            }
        }
        
        def engine = new SimpleTemplateEngine()
        def template = engine.createTemplate(text).make(context)
        
        return template.toString()
    }
    
    String renderException(Throwable ex, HttpServletRequest req) {
    
        def stackInfo = decodeStackTrace(ex)
                  
        return render('exception.html', [
            title: ex.class.name,
            message: ex.message,
            metadata: [
                'Request Method': req.method.toUpperCase(),
                'Request URL': req.requestURL,
                'Exception Type': ex.class.name,
                'Exception Location': "${stackInfo.rootCause.fileName}, line ${stackInfo.rootCause.lineNumber}",
            ],
            stacktrace: stackInfo.html
        ])
    }
    
    private def decodeStackTrace(Throwable t) {
    
        // FIXME
        // this doesn't really make sense, but I'm not sure
        // how to create a `firstPartyPrefixes` list.
        def thirdPartyPrefixes = ['sun', 'java', 'groovy', 'org.codehaus', 'org.mortbay']
    
        String html = '';
        html += t.toString() + '\n'
        StackTraceElement rootCause = null
        
        for(StackTraceElement ste : t.getStackTrace()) {
            if(thirdPartyPrefixes.any { ste.className.startsWith(it) }) {
                html += "<span class='stack-thirdparty'>        at ${ste}\n</span>"
            } else {
                html += "        at ${ste}\n"
                if(null == rootCause) rootCause = ste
            }
        }
                
        return [html: html, rootCause: rootCause]
    }
}
