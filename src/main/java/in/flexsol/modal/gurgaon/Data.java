
package in.flexsol.modal.gurgaon;

import java.util.List;

public class Data {

    public String text;
    public String alert;
    public String color;
    public Integer value;
    public String updated;
    public String temp;
    public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getClouds() {
		return clouds;
	}
	public void setClouds(String clouds) {
		this.clouds = clouds;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getDominating() {
		return dominating;
	}
	public void setDominating(String dominating) {
		this.dominating = dominating;
	}
	public List<AqiParam> getAqiParams() {
		return aqiParams;
	}
	public void setAqiParams(List<AqiParam> aqiParams) {
		this.aqiParams = aqiParams;
	}
	public Object content;
    public String country;
    public String clouds;
    public Coordinates coordinates;
    public Source source;
    public String accuracy;
    public String dominating;
    public List<AqiParam> aqiParams = null;

}
