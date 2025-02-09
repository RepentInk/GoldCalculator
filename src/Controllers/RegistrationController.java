package Controllers;

import Helpers.HelperFunctions;
import Models.Registration;
import Repository.RegistrationRepository;

/**
 *
 * @author nyark
 */
public class RegistrationController {

    RegistrationRepository registrationRepository = new RegistrationRepository();
    HelperFunctions helper = new HelperFunctions();

    public void saveUpdate(
            int registrationID,
            String code,
            String startDate,
            String endDate,
            boolean status
    ) {

        if (registrationID > 0) {
            Registration registration = new Registration(
                    registrationID,
                    code,
                    status,
                    startDate,
                    endDate
            );

            registrationRepository.update(registration, registrationID);
        } else {
            Registration registration = new Registration(
                    code,
                    status,
                    startDate,
                    endDate
            );

            registrationRepository.save(registration);
        }

    }

    public Registration getRegistration() {
        return registrationRepository.find(0);
    }

    public Registration checkActivationKey(int keyCodeId) {
        return registrationRepository.find(keyCodeId);
    }

    public void updateRegistration(int id, boolean status) {
        Registration registration = registrationRepository.find(id);
        registration.setStatus(status);

        registrationRepository.update(registration, id);
    }

    public void updateRegistrationCode(int id, String code) {
        Registration registration = registrationRepository.find(id);
        registration.setCode(code);

        registrationRepository.update(registration, id);
    }

    public boolean checkActivation(String shopName) {
        if (shopName.equals("")) {
            return false;
        }
        boolean valid = false;
        Registration registration = getRegistration();
        String encode = helper.generateActivationCode(shopName);

        if (registration.getCode().equalsIgnoreCase(encode)) {
            valid = true;
        }

        return valid;
    }

}
