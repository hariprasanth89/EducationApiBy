package com.MyComp.EducationApi.utility;

public class Geocoding {
//
//	public static void main(String[] args) throws Exception {
//		updateGeocode();
//		  /*RestTemplate rest = new RestTemplate(); String url =
//		  "http://services.gisgraphy." +
//		  "com/geocoding/geocode?address=sunnyvale&houseNumber=&" +
//		  "streetName=south%20fairoaks" +
//		  "&city=Sunnyvale&state=&zipCode=94086&__checkbox_postal=true&country=US";
//		  GeocodeResponseBean geocodeResponseBean = rest.getForObject(url,
//		  GeocodeResponseBean.class);
//		  System.out.println(geocodeResponseBean.getGeoAddressBean());*/
//		 
//		/*AddressBean addressBean = new AddressBean();
//		addressBean.setStreetName("south fairoaks");
//		addressBean.setCity("Sunnyvale");
//		addressBean.setZip("94086");
//		double[] geocode = getGeocode(addressBean);
//		System.out.println(geocode);*/
//	}

//	public static BigDecimal[] getGeocode(Address address) {
//
//		String queryString = getQueryString(address);
//		RestTemplate rest = new RestTemplate();
//		String url = "http://services.gisgraphy.com/geocoding/geocode?" + queryString;
//		System.out.println(url);
//		GeocodeResponseBean geocodeResponseBean = null;
//		try {
//			geocodeResponseBean = rest.getForObject(url, GeocodeResponseBean.class);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		BigDecimal[] geocode = new BigDecimal[2];
//		if (geocodeResponseBean != null) {
//			List<GeocodeAddressBean> geoAddressBeanList = geocodeResponseBean.getGeoAddressBean();
//			if (geoAddressBeanList != null && geoAddressBeanList.size() > 0) {
//				
//				geocode[0] = new BigDecimal(geoAddressBeanList.get(0).getLat(),MathContext.DECIMAL64);
//				geocode[1] = new BigDecimal(geoAddressBeanList.get(0).getLng(),MathContext.DECIMAL64);
//			}
//		}else{
//			geocode[0] = new BigDecimal(0);
//			geocode[1] = new BigDecimal(0);
//		}
//		return geocode;
//	}

//	private static String getQueryString(Address address) {
//
//		StringBuffer queryString = new StringBuffer();
//
//		/*if (address.getStreetName() == null) {
//			throw new Exception("Street Address is empty");
//		}*/
//
//		queryString.append("address=" + address.getCity() + "&");
//		queryString.append("city=" + address.getCity() + "&");
//		queryString.append("state=" + address.getState() + "&");
//		queryString.append("zipCode=" + address.getZIP() + "&");
//		queryString.append("country=US");
//
//		return queryString.toString();
//	}
	
//	public static void updateGeocode() throws SQLException{
//		Connection con  = null;
//		Statement stmt = null;
//		ResultSet rs= null;
//		Statement stmt1 = null;
//		try{
//		Class.forName("com.mysql.jdbc.Driver");
//		 con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "Nestlings", "#3Nestlings45#");
//		 //con.setAutoCommit(false);
//		 stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//		 stmt1 = con.createStatement();
//		 rs= stmt.executeQuery("select * from address");
//		while(rs.next()){
//			Address address = new Address();
//			address.setStreetName(rs.getString("streetname"));
//			address.setCity(rs.getString("city"));
//			address.setZIP(rs.getString("zip"));
//			address.setState(rs.getString("state"));
//			address.setLatitude(rs.getBigDecimal("latitude"));
//			address.setLongitude(rs.getBigDecimal("longitude"));rs.getInt("addressid");
//			address.setAddressID(rs.getInt("addressid"));
//			if(address.getLatitude() == null || address.getLatitude().doubleValue() == 0){
//			BigDecimal[] geocode = getGeocode(address);
//			int addressid = rs.getInt("addressid");
//			//rs.updateDouble("latitude", geocode[0]);
//			//rs.updateDouble("longitude", geocode[1]);
//			System.out.println("update address set latitude="+geocode[0]+",longitude ="+geocode[1]+" where addressid="+addressid);
//			stmt1.executeUpdate("update address set latitude="+geocode[0]+",longitude ="+geocode[1]+" where addressid="+addressid);
//			}
//			//con.commit();
//			Thread.sleep(10000);
//		}
//		
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			//con.commit();
//			rs.close();
//			stmt.close();
//			stmt1.close();
//			con.close();
//		}
//	}

}
