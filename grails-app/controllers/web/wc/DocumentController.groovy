package web.wc

import grails.converters.JSON

class DocumentController {

    DocumentService documentService

    def index() {
        // TODO: don't pass the domain objects
        [documentInstanceList: Document.all]
    }

    def show() {
        Boolean filter = params.filter as boolean
        if (chainModel != null)
            return chainModel
        Document document = Document.get(params.id)
        return buildModel(document, filter)
    }

    def save() {
        Boolean filter = params.filter as boolean
        try {
            if (!params.url.startsWith("http://"))
                params.url = "http://" + params.url
            Document document = documentService.retrieve(params.url)
            chain(
                    action: "show",
                    model: buildModel(document, filter),
                    params: [id: document.id, filter: filter]
            )
        }
        catch (Exception e) {
            // quietly fail back to the index page (e.g. if url is blank)
            redirect(action: 'index', params: [filter: filter])
        }
    }

    private static LinkedHashMap<String, Object> buildModel(Document document, boolean filter) {
        [url   : document.getShortUrl(),
         topTen: document.getTopTenWords(filter),
         words : document.getWords(filter) as JSON,
         total : document.getWordCount()]
    }

}
