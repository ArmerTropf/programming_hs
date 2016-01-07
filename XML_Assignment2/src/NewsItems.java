/***
 * Klasse zum speichern der jeweiligen Daten eines Feeds
 * Die Daten dieser Klasse werden durch die verschiedenen
 * Abfragemechanismen wie SAX oder StAX-Cursor API befüllt 
 * @author Schriever/Günster
 * 
 * {@value}
 */
class NewsItems 
{
	  private String title;
	  private String description;
  
	  public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
