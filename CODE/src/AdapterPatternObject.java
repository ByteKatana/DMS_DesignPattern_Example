
//============================================================================
//Name        : AdapterPatternObject.java
//
//The classes and/or objects participating in this pattern are:
//	1. Target   (WordDoc)
//			Defines the domain-specific interface that ?????????  uses.
//	2. Adapter   (ConvertWordToPDF)
//			Adapts the interface Adaptee to the Target interface.
//	3. Adaptee   (PDFDoc)
//			Defines an existing interface that needs adapting.
//	4. Client   (??????)
//			Collaborates with objects conforming to the Target (WordDoc)
//			interface.
//
//This uses Objects Adapter.
//============================================================================

//This is the "Target" class.
//Word Document File. Our
//document is written to be
//displayed with a Word Document File Viewer.

interface WordDoc
{
	String getWordFile();
}
//This is the "Adaptee" class. PDF Document File
//This document will be displayed in PDF File format.

class PDFDoc
{
	public String getPDFFile()
	{
		WorkOrderDoc workOrderDoc = new WorkOrderDoc("Word File");
		System.out.println("Your"+workOrderDoc.getName()+" has been converted to PDF File");
		return "Conversion Succeeded";
	}
}

// This is the "Adapter" class. ConvertWordToPDF.
// We need a connector so we can also display Word Documents 
// via converting them to PDF format.

class ConvertWordToPDF implements WordDoc
{
	public String getWordFile()
	{
		// Possibly do some other work and then call
		// getPDFFile from the PDFDoc.
		String document = _adaptee.getPDFFile();
		return document;
	}

	ConvertWordToPDF(PDFDoc adaptee)
	{
		_adaptee = adaptee;
	}

	private PDFDoc _adaptee;
}

// This is our client which will be using
// the Target (WordDoc) Interface.

 class AdapterPatternObject
{
	/*public static void main(String[] args)
	{
		// Create adapter and place a request
		WordDoc document = new ConvertWordToPDF(new PDFDoc());
		// document is-a Word document. So our Word Document
		// can connect.
		@SuppressWarnings("unused")
		String output = document.getWordFile();
	}*/
}
