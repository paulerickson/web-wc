package web.wc

import groovyx.net.http.HTTPBuilder

//@Transactional
class DocumentService {

    private final HTTPBuilder http

    public DocumentService() {
        this.http = new HTTPBuilder()
    }

    Document retrieve(String url) {
        Document document = Document.findByUrl(url)
        if (document == null) {
            document = retrieveFromWeb(url)
            document.save(flush: true)
        }
        return document
    }

    Document retrieveFromWeb(String url) {
        return new Document(url, http.get(uri: url).toString())
    }

}
