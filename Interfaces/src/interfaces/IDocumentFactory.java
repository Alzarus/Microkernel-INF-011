/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

//• IDocumentEditor (visualiza o documento)
//• IDocumentValidator (valida o documento)
//• IDocumentSerializer (grava e lê o documento no disco)

/**
 *
 * @author pedro
 */
public interface IDocumentFactory {
    // Metodos para criacao dos tres produtos
    public IDocumentEditor getDocumentEditor();
    public IDocumentValidator getDocumentValidator();
    public IDocumentSerializer getDocumentSerializer();
    public String getSupportedExtensions(); // return "gif|png|jpg";
}