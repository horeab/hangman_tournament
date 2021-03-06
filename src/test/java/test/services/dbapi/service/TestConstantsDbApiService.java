package test.services.dbapi.service;

import java.util.Map;

import libgdx.ui.services.dbapi.ConstantsDbApiService;
import libgdx.ui.services.dbapi.UsersDbApiService;
import test.services.TestUtils;

public class TestConstantsDbApiService extends ConstantsDbApiService {

    @Override
    public String formParametersString(Map<String, Object> params) {
        return TestUtils.formTestParametersString(super.formParametersString(params));
    }

    @Override
    public String getBaseUrl() {
        return TestUtils.getBaseUrl();
    }

}
