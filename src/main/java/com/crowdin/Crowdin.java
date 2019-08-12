package com.crowdin;

import com.crowdin.client.api.ProjectsApi;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Pageable;
import com.crowdin.util.PaginationUtil;

import java.io.IOException;

public class Crowdin {

    public static void main(String[] args) throws IOException {
        Settings settings = Settings.withBaseUrl(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJhY2Nlc3MtdG9rZW4iLCJqdGkiOiI5ZGFiYjcyNDMzOWM3ODA5MWUwNWZhOGU0OGRjY2VmMjMwYTliOTA4MDkyYzUwZTU3MDQxZTVjMzg1YTQzOTBkNzI1MTQ4YWIwNjE2NDViMSIsImlhdCI6MTU2NTU1NzY5NywibmJmIjoxNTY1NTU3Njk3LCJleHAiOjE1OTcxODAwOTcsInN1YiI6IjIiLCJkb21haW4iOiJNYW5nbyIsInNjb3BlcyI6W10sImFzc29jaWF0aW9ucyI6WyIqIl0sInNlc3Npb24iOjB9.JQ7tS_kWGtaWzdqwkw5mxWn1O2x-BH3mb5jdcqAm1n8lgZU4N966CilE3eHf8vY5ENH5LFSJkAVMiu4_hv3fpI1IduPwTFXEkHQELS8Nb715sfRTVQTyfHsJvRrljeZ91tKzx_FijruS3dvjoAxbtmTYkHLvYYdfHodMYOs_BOB5g2SQbjiIilE44DKfuH-X-EHEp5lurzfN1UUJcjgU38PE-LTpT3cmAEc5s_Jyzoor98awV2ULGaojf3FiHK4xQfeRFDycKffewqH5T4HwafXMnVQCDRY2bkPk6Atjrh5ihQAhMO8BbwUudParu19O0HMQJhsInOM2FY8ClWWc8JiEr8MwH2XfCR4uoaYYPXoPAQaQ7flU-bnbj98piCnx_A1z4P2CgnBNPAUMxRRXaITDhc7j_apLLpycbh4e0Z29ZGa84xshmRbxyyF8c_5Mcxadgg_yIANnZvmti3dYTiNZ8fklmTZVbnJbMcc6wP8Jb0z_yfSI1DguHi92OQdqhUBet65lYk7HSRnVrkZJBNr5bNULbsRcI80bnRKh9WpqNjbywDtYSv9EzgpFwpSAeLVowx3jdHxz2XqH8cTBg4cXDcrVVyxBxjAdcilQXRI9b_wQfDRSsQxUmukreufvRD2UWAgC-hZ2zO6r2KmaYans7RFNdfGK4NKO_3s4Bi8",
                "vasyl",
                "https://vasyl.crowdin.com/api/v2"
        );

        PaginationUtil.unpaged(new ProjectsApi(settings).getRootGroupProjects(Pageable.unpaged())).stream()
                .forEach(project -> {
                    System.out.println(project.getId());
                    System.out.println(project.getName());
                });

    }
}
