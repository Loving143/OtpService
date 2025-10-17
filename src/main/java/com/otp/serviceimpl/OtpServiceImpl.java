package com.otp.serviceimpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otp.dto.ValidateOtpRequest;
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
        otp.setOtp(otpCode);
        otp.setExpiresAt(Instant.now()); // 5 minutes expiry
//        otp.setAttempts(null);
        otp.setType("EMAIL");
        return otpRepository.save(otp);
	}
	@Override
	public boolean validateOtp(String userName, String otp) {
		
		Optional<Otp> otpOpt = otpRepository.findByUserNameAndOtp(userName,otp);
		System.out.println(otpOpt.get().getOtp()+"bndf"+otp);
        if (otpOpt.isPresent() && otpOpt.get().getOtp().equals(otp) && otpOpt.get().getExpiresAt().isAfter(Instant.now())) {
            return true;
        }
        return false;
	}
	

}
