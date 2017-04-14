/**
 * 
 */
package com.viksitpro.core.dao.entities.utils.address;

import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.Address;
import com.viksitpro.core.dao.entities.AddressDAO;
import com.viksitpro.core.dao.entities.Pincode;
import com.viksitpro.core.dao.entities.PincodeDAO;
import com.viksitpro.core.exceptions.CustomException;
import com.viksitpro.core.utilities.CustomExceptionCodes;

/**
 * @author ComplexObject
 *
 */
public class AddressService {

	public Address getAddress(Integer addressId)
	{
		AddressDAO addressDAO = new AddressDAO();
		Address address;
		if(addressId != null) {
			address = addressDAO.findById(addressId);
		} else {
			address = null;
		}
		return address;	
	}
	
	/**
	 * This method will be called when pincode is not selected from database.
	 * Pincode is created first then address is created.
	 * 
	 @param addressLine1
	 *            String value of address line 1 (Max 255 characters)
	 * @param addressLine2
	 *            String value of address line 2 (Max 255 characters)
	 * @param pin
	 *            integer value of pincode
	 * @param city 
	 * 			  String value of city	
	 * @param country
	 * 			  String value of country	
	 * @param state
	 * 			  String value of state	
	 * @return
	 * 			  Address Object	
	 */
	public Address createAddress(String addressLine1, String addressLine2, String city, String country, Integer pin, String state)
	{
		Address address = new Address();
		HashMap<String, Double> data = getLatLongFromPincode(pin);
		Double longitude = data.get("longitude");
		Double latitude = data.get("latitude");
		address.setAddressGeoLatitude(latitude);
		address.setAddressGeoLongitude(longitude);
		address.setAddressline1(addressLine1);
		address.setAddressline2(addressLine2);				
		Pincode pincode = createPincode( city,  country,  pin, state);
		address.setPincode(pincode);
		saveAddressToDAO(address);		
		return address; 		
	}
	
	
	/**
	 * @param city String value of city
 	 * @param country String value of country
	 * @param pin  Integer value of pincode
	 * @param state String value of state
	 * @return returns Pincode object on success and throws exception in case of error.
	 */
	private Pincode createPincode(String city, String country, Integer pin, String state) {
		Pincode pincode = new Pincode();
		pincode.setCity(city);
		pincode.setCountry(country);
		
		HashMap<String, Double> data = getLatLongFromPincode(pin);
		Double longitude = data.get("longitude");
		Double latitude = data.get("latitude");
		pincode.setLattiude(latitude);
		pincode.setLongitude(longitude);
		pincode.setPin(pin);
		pincode.setState(state);		
		pincode = savePinCodeToDAO(pincode);		
		return pincode;
	}


	/**
	 * @param pincode pincode object
	 * @return returns pincod object after saving in database 
	 */
	private Pincode savePinCodeToDAO(Pincode pincode) {
		PincodeDAO pincodeDAO = new PincodeDAO();

		Session pincodeSession = pincodeDAO.getSession();
		Transaction pincodeTransaction = null;
		try {
			pincodeTransaction = pincodeSession.beginTransaction();
			pincodeSession.save(pincode);
			pincodeTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (pincodeTransaction != null)
				pincodeTransaction.rollback();
			e.printStackTrace();
			String message = "Unable to save pincode in database";
			String code = CustomExceptionCodes.INTERNAL_SERVER_ERROR;
			String error = e.getMessage() != null ? e.getMessage()
					: e.getLocalizedMessage() != null ? e.getLocalizedMessage()
							: "Unknown Error in com.viksitpro.core.dao.entities.utils.address.savePinCodeToDAO";
			throw new CustomException(message, code, error);
		} finally {
			pincodeSession.close();
		}
		return pincode;
	}


	/**
	 * This method will be called when pincode is selected from database. 
	 * This method creates an address in database and returns that object. In
	 * case pincode provided as parameter is not present in database, method
	 * will throw exception.
	 * 
	 * @param addressLine1
	 *            String value of address line 1 (Max 255 characters)
	 * @param addressLine2
	 *            String value of address line 2 (Max 255 characters)
	 * @param pincode
	 *            integer value of pincode
	 * @return returns Address Object
	 */
	public Address createAddress(String addressLine1, String addressLine2, Integer pincode) {
		Address address = new Address();
		HashMap<String, Double> data = getLatLongFromPincode(pincode);
		Double longitude = data.get("longitude");
		Double latitude = data.get("latitude");

		address.setAddressGeoLatitude(latitude);
		address.setAddressGeoLongitude(longitude);
		address.setAddressline1(addressLine1);
		address.setAddressline2(addressLine2);

		Pincode pin = getPincode(pincode);
		address.setPincode(pin);

		saveAddressToDAO(address);
		return address;
	}
	
	public Address updateAddress(Integer addressId, String addressLine1, String addressLine2, Integer pincode) {
		
		Address address = getAddress(addressId);
		
		HashMap<String, Double> data = getLatLongFromPincode(pincode);
		Double longitude = data.get("longitude");
		Double latitude = data.get("latitude");

		address.setAddressGeoLatitude(latitude);
		address.setAddressGeoLongitude(longitude);
		address.setAddressline1(addressLine1);
		address.setAddressline2(addressLine2);

		Pincode pin = getPincode(pincode);
		address.setPincode(pin);

		updateAddressToDAO(address);
		return address;
	}

	public Address saveAddressToDAO(Address address) {

		AddressDAO addressDAO = new AddressDAO();

		Session addressSession = addressDAO.getSession();
		Transaction addressTransaction = null;
		try {
			addressTransaction = addressSession.beginTransaction();
			addressSession.save(address);
			addressTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (addressTransaction != null)
				addressTransaction.rollback();
			e.printStackTrace();
			String message = "Unable to save address in database";
			String code = CustomExceptionCodes.INTERNAL_SERVER_ERROR;
			String error = e.getMessage() != null ? e.getMessage()
					: e.getLocalizedMessage() != null ? e.getLocalizedMessage()
							: "Unknown Error in com.viksitpro.core.dao.entities.utils.address.saveAddressToDAO";
			throw new CustomException(message, code, error);
		} finally {
			addressSession.close();
		}
		return address;
	}

	public Address updateAddressToDAO(Address address) {

		AddressDAO addressDAO = new AddressDAO();

		Session addressSession = addressDAO.getSession();
		Transaction addressTransaction = null;
		try {
			addressTransaction = addressSession.beginTransaction();
			addressSession.update(address);
			addressTransaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (addressTransaction != null)
				addressTransaction.rollback();
			e.printStackTrace();
			String message = "Unable to update address in database";
			String code = CustomExceptionCodes.INTERNAL_SERVER_ERROR;
			String error = e.getMessage() != null ? e.getMessage()
					: e.getLocalizedMessage() != null ? e.getLocalizedMessage()
							: "Unknown Error in com.viksitpro.core.dao.entities.utils.address.saveAddressToDAO";
			throw new CustomException(message, code, error);
		} finally {
			addressSession.close();
		}
		return address;
	}

	/**
	 * 
	 * @param pincode
	 *            Integer Pincode value
	 * @return returns Pincode object in case of success or throws exception in
	 *         case of no record is found in database.
	 */

	public Pincode getPincode(Integer pincode) {
		PincodeDAO pindao = new PincodeDAO();
		List<Pincode> pincodes = pindao.findByPin(pincode);
		if (pincodes.size() > 0) {
			return pincodes.get(0);
		} else {
			String message = "Could not find Pincode with pin {" + pincode + "}";
			String code = CustomExceptionCodes.RESOURCE_NOT_FOUND;
			String error = message;
			throw new CustomException(message, code, error);
		}

	}

	/*
	 * This method returns the Latitude and Longitude from pincode. Latitude :
	 * key {latitude}, value: {latitude} Longitude : key {longitude}, value:
	 * {longitude}
	 */

	public HashMap<String, Double> getLatLongFromPincode(Integer pincode) {
		HashMap<String, Double> data = new HashMap<String, Double>();
		data.put("longitude", 77.55209);
		data.put("latitude", 13.0356122);
		return data;
	}

}
