package capstone.everyhealth.service;

import capstone.everyhealth.controller.dto.Stakeholder.AdminEditRequest;
import capstone.everyhealth.controller.dto.Stakeholder.AdminFindResponse;
import capstone.everyhealth.controller.dto.Stakeholder.AdminLoginRequest;
import capstone.everyhealth.domain.report.ChallengeAuthPostReport;
import capstone.everyhealth.domain.report.SnsCommentReport;
import capstone.everyhealth.domain.report.SnsPostReport;
import capstone.everyhealth.domain.stakeholder.Admin;
import capstone.everyhealth.exception.stakeholder.AdminLoginFailed;
import capstone.everyhealth.exception.stakeholder.AdminNotFound;
import capstone.everyhealth.repository.AdminRepository;
import capstone.everyhealth.repository.ChallengeAuthPostReportRepository;
import capstone.everyhealth.repository.SnsCommentReportRepository;
import capstone.everyhealth.repository.SnsPostReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final SnsPostReportRepository snsPostReportRepository;
    private final SnsCommentReportRepository snsCommentReportRepository;
    private final ChallengeAuthPostReportRepository challengeAuthPostReportRepository;

    @Transactional
    public Long save(Admin admin) {
        return adminRepository.save(admin).getId();
    }

    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    @Transactional
    public void updateAdmin(Long adminId, AdminEditRequest adminEditRequest) throws AdminNotFound {

        Admin admin = adminRepository.findById(adminId).orElseThrow(()->new AdminNotFound(adminId));

        updateAdminField(adminEditRequest, admin);
    }

    @Transactional
    public void deleteAdmin(Long adminId){
        adminRepository.deleteById(adminId);
    }

    private void updateAdminField(AdminEditRequest adminEditRequest, Admin admin) {
        admin.setAdminPassword(adminEditRequest.getAdminPassword());
        admin.setAdminName(adminEditRequest.getAdminName());
        admin.setAdminPhoneNumber(adminEditRequest.getAdminPhoneNumber());
    }

    public void adminLoginValidation(AdminLoginRequest adminLoginRequest) throws AdminLoginFailed {
        validateAdminLoginRequest(adminLoginRequest);
    }

    public List<SnsPostReport> findAllSnsPostReports() {
        return snsPostReportRepository.findAll();
    }

    public List<SnsCommentReport> findAllSnsCommentReports() {
        return snsCommentReportRepository.findAll();
    }

    public List<ChallengeAuthPostReport> findAllChallengeAuthPostReports() {
        return challengeAuthPostReportRepository.findAll();
    }

    private void validateAdminLoginRequest(AdminLoginRequest adminLoginRequest) throws AdminLoginFailed {
        adminRepository.findByAdminIdAndAdminPassword(adminLoginRequest.getAdminId(), adminLoginRequest.getAdminPassword()).orElseThrow(()->new AdminLoginFailed());
    }
}