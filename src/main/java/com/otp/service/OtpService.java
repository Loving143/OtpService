package com.otp.service;

import com.otp.entity.Otp;

public interface OtpService {

	Otp generateOtp(String userName);

}
