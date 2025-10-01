package com.otp.serviceimpl;

import java.time.Instant;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otp.entity.Otp;
import com.otp.repository.OtpRepository;
import com.otp.service.OtpService;
@Service
public class OtpServiceImpl implements OtpService{

	@Autowired
	OtpRepository otpRepository;
	@Override
	public Otp generateOtp(String userName) {
		String otpCode = String.format("%06d", new Random().nextInt(999999));
        Otp otp = new Otp();
        otp.setUserName(userName);
        otp.setCodeHash(otpCode);
        otp.setExpiresAt(Instant.now()); // 5 minutes expiry
//        otp.setAttempts(null);
        otp.setType("EMAIL");
        return otpRepository.save(otp);
	}

}
