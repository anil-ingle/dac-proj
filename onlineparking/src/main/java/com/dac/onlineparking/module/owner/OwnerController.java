package com.dac.onlineparking.module.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerController {

	@Autowired
	private OwnerService service;

	@RequestMapping(value = "mvc/ownerAddr", method = RequestMethod.GET)
	public @ResponseBody OwnerAddressInfoVO ownerWallet(@RequestParam("userId") int userId) {
		return service.ownerAddress(userId);
	}

	@RequestMapping(value = "mvc/ownerWallet", method = RequestMethod.GET)
	public @ResponseBody OwnerWalletVO ownerAddress(@RequestParam("ownerId") int ownerId) {
		return service.ownerWallet(ownerId);
	}
}
