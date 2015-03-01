import web.wc.DocumentService

class BootStrap {

    def init = { servletContext ->
        // Prefetch some example pages
        try {
            def documentService = new DocumentService()
            documentService.retrieve("http://en.wikipedia.org")
            documentService.retrieve("http://c2.com")
            documentService.retrieve("http://catb.org/jargon/html/S/spam.html")
        }
        catch (Exception e) {
            print e
        }
    }
    def destroy = {
    }
}
