var please = function(saveGbn) {

    var chkAjax = new keis.ajax("/kua/mypage/getBua0360NtLastUpdtDtAjax.do", "POST", "json");
    chkAjax.addParam("iapNo", $("#execRptFrm > input[name='iapNo']").val());
    chkAjax.addParam("reptTme", 5);

    chkAjax.send(function f_resposeGetBua0360NtLastUpdtDtAjax(result) {

        var resultObj = result.ajaxResult;

        // DB에 수정일자가 존재하고 기존 조회해온 값이랑 다를경우
        if(resultObj != null && resultObj != $("#lastUpdtDt").val()) {
            alert("수정된 내용이 있습니다. 확인하신 후 다시 등록하시기 바랍니다.");
            return;
        } else {

            var currDate = '20210605';
            var appnDe = '20210605';
            var appnDeReg = '20210605';
            if (saveGbn == "2" && (appnDe > currDate || appnDeReg < currDate)) {
                alert("지정일에만 제출이 가능합니다.")
                return false;
            }

            var rgstSecd = '';
            if(rgstSecd == '20' || rgstSecd == '21'){
                alert("관할 기관에서 등록 중인 건이 있습니다.\n관할 기관으로 문의하시기 바랍니다.");
                return false;
            }

            var cfmMsg = "";
            var bErrPeriod = false;
            var bChkFileSize = false;
            var bErrSalOccur = false;
            var fileSize = 0;

            var sporPrdBgde = "";
            var sporPrdEnde = "";
            var prgmBgde = "";
            var prgmEnde = "";
            var orgPrgmBgde = "";
            var orgPrgmEnde = "";
            var icomOcrnDe = "";
            var empmDe = "";

            var reptTme = "";
            var reptStcd = "";

            var regCnt = 0;

            var empmStmnYn = "";


            $("input[name$='jhacCn']").each(function(idx) {

                var jhacCn = $("[name$='jhacCn']").eq(idx).val();
                var jhntAcvn = $("[name$='jhntAcvn']").eq(idx).val();
                var prgmBgde = $("[name$='jhacPrgmBgde']").eq(idx).val();
                var prgmEnde = $("[name$='jhacPrgmEnde']").eq(idx).val();

                if(jhacCn != "" || jhntAcvn != "" || prgmBgde != "" || prgmEnde != ""){
                    regCnt++;
                }

            });

            //취업지원 프로그램 선택 값 ADD
            regCnt += $("#tbodyExecPgm").find("input[name$='.prgmSn']").length;

            //21.3.24 취업여부값 확인
            empmStmnYn = $("input:radio[name='empmStmnYn']:checked").val();

            if(regCnt < 1 && empmStmnYn == "N") {

                alert("취업활동계획 이행 보고서 및 구직활동 이행 보고서를\n1회 이상 등록 하셔야합니다.");
                location.href = "#bodyEmpSptPgm";
                return false;
            }


            $("#tbodyExecPgm").find("input[name$='.prgmBgde']").each(function(idx) {

                prgmBgde = $(this).val();
                prgmEnde = $("#tbodyExecPgm").find("input[name$='.prgmEnde']").eq(idx).val();
                orgPrgmBgde = $("#tbodyExecPgm").find("input[name$='.orgPrgmBgde']").eq(idx).val();
                orgPrgmEnde = $("#tbodyExecPgm").find("input[name$='.orgPrgmEnde']").eq(idx).val();

                if (!bErrPeriod && prgmBgde < orgPrgmBgde) {

                    alert("취업활동계획 이행 보고서의 시작일자는 " + orgPrgmBgde.substring(0, 4) + "년 " + orgPrgmBgde.substring(4, 6) + "월 " + orgPrgmBgde.substring(6, 8) + "일 이후 일자이어야 합니다.");
                    $(this).focus();
                    bErrPeriod = true;
                }

                if (!bErrPeriod && prgmEnde > orgPrgmEnde) {

                    alert("취업활동계획 이행 보고서의 종료일자는 " + orgPrgmEnde.substring(0, 4) + "년 " + orgPrgmEnde.substring(4, 6) + "월 " + orgPrgmEnde.substring(6, 8) + "일 이전 일자이어야 합니다.");
                    $("#tbodyExecPgm").find("input[name$='.prgmEnde']").eq(idx).focus();
                    bErrPeriod = true;
                }

                if (!bErrPeriod && prgmBgde > prgmEnde) {

                    alert("취업활동계획 이행 보고서의 시작일자는 종료일자 이전 일자이어야 합니다.");
                    $(this).focus();
                    bErrPeriod = true;
                }
            });

            if (bErrPeriod) {

                return false;
            }


            $("input[name$='jhacCn']").each(function(idx) {

                var jhacSporSecd = $("[name$='jhacSporSecd']").eq(idx).val();
                var jhacCn = $("[name$='jhacCn']").eq(idx).val();
                var jhntAcvn = $("[name$='jhntAcvn']").eq(idx).val();
                var prgmBgde = $("[name$='jhacPrgmBgde']").eq(idx).val();
                var prgmEnde = $("[name$='jhacPrgmEnde']").eq(idx).val();

                if(jhacSporSecd != "" || jhacCn != "" || jhntAcvn != "" || prgmBgde != "" || prgmEnde != ""){
                    if (!f_kuaCheckMuliValidation($("[name$='jhacSporSecd']").eq(idx).attr("name"), 0, "구직활동 구분을 선택하시기 바랍니다.")) {

                        bErrSalOccur = true;
                        return false;
                    }

                    if (!f_kuaCheckMuliValidation($("[name$='jhacCn']").eq(idx).attr("name"), 0, "활동명을 입력하시기 바랍니다.")) {

                        bErrSalOccur = true;
                        return false;
                    }

                    if (!f_kuaCheckMuliValidation($("[name$='jhacPrgmBgde']").eq(idx).attr("name"), 0, "구직활동 시작일을 입력하시기 바랍니다.")) {

                        bErrSalOccur = true;
                        return false;
                    }

                    if (!f_kuaCheckMuliValidation($("[name$='jhacPrgmEnde']").eq(idx).attr("name"), 0, "구직활동 종료일을 입력하시기 바랍니다.")) {

                        bErrSalOccur = true;
                        return false;
                    }

                    if(prgmBgde > prgmEnde) {
                        alert("구직활동 시작일은 종료일보다 클 수 없습니다.")
                        bErrSalOccur = true;
                        return false;
                    }

                    if (!f_kuaCheckMuliValidation($("[name$='jhntAcvn']").eq(idx).attr("name"), 0, "활동내용을 입력하시기 바랍니다.")) {

                        bErrSalOccur = true;
                        return false;
                    }
                }

            });

            if (bErrSalOccur) {

                return false;
            }


            $("#fileList").find("input").each(function(idx) {

                if ($(this).attr("type") == "file") {

                    fileSize = Math.floor(this.files[0].size / 1024 / 1024);

                    if (!bChkFileSize && fileSize > 5) {

                        bChkFileSize = true;
                    }
                }
            });

            if (bChkFileSize) {

                alert("취업활동계획이행보고 증빙 서류의 첨부용량이 5MB 를 넘었습니다. 확인하시기 바랍니다.");
                return false;
            }

            if (!f_kuaCheckValidation("icomOcrnStmnYn", "radio", "소득 발생 여부를 선택하시기 바랍니다.")) {

                return false;
            }

            sporPrdBgde = $("#execRptFrm > input[name='sporPrdBgde']").val();
            sporPrdEnde = $("#execRptFrm > input[name='sporPrdEnde']").val();

            if ($("input:radio[name='icomOcrnStmnYn']:checked").val() == "Y") {


                $("input[name$='icomOcrnAmt']").each(function(idx) {

                    if (!f_kuaCheckMuliValidation("kuaSalOccurList[" + idx + "].icomOcrnAmt", 0, "소득액을 입력하시기 바랍니다.")) {

                        bErrSalOccur = true;
                        return false;
                    }

                    if (!f_kuaCheckMuliValidation("kuaSalOccurList[" + idx + "].icomCn", 0, "소득내용을 입력하시기 바랍니다.")) {

                        bErrSalOccur = true;
                        return false;
                    }

                    if (!f_kuaCheckMuliValidation("kuaSalOccurList[" + idx + "].icomOcrnDe", 0, "발생일을 입력하시기 바랍니다.")) {

                        bErrSalOccur = true;
                        return false;
                    } else {

                        icomOcrnDe = $("input[name='kuaSalOccurList[" + idx + "].icomOcrnDe']").val();


                    }
                });

                if (bErrSalOccur) {

                    return false;
                }
            }

            if (!f_kuaCheckValidation("empmStmnYn", "radio", "취업 여부를 선택하시기 바랍니다.")) {

                return false;
            }

            if ($("input:radio[name='empmStmnYn']:checked").val() == "Y") {


                if (!f_kuaCheckValidation("empmStleCd", "select", "취업형태를 선택하시기 바랍니다.")) {

                    return false;
                } else if ($("#empmStleCd").val() == "99" && $.trim($("#empmStleEtct").val()) == "") {

                    alert("취업형태 기타 내용을 입력하시기 바랍니다.");
                    $("#empmStleEtct").focus();
                    return false;
                }

                if (!f_kuaCheckValidation("empmDe", "input", "취업(예정)일자를 입력하시기 바랍니다.")) {

                    return false;
                } else {

                    empmDe = $("#empmDe").val();

                    if (empmDe < sporPrdBgde || empmDe > sporPrdEnde) {


                        alert("취업(예정)일이 지급주기 기간 사이의 일자가 아닙니다.");
                        $("#empmDe").focus();
                        return false;
                    }
                }

                if (!f_kuaCheckValidation("whct", "input", "주당 근로시간을 입력하시기 바랍니다.")) {

                    return false;
                }

                if (!f_kuaCheckValidation("workCopn", "input", "근무처를 입력하시기 바랍니다.")) {

                    return false;
                }
            }

            reptTme = $("#execRptFrm > input[name='reptTme']").val();

            if (saveGbn == "1") {

                if(($("#pstnRsveYn").val() == "Y" || $("input:radio[name='pstnRsveYn']:checked").val() == "Y") && !f_payApplValidChk()) {
                    return false;
                }

                reptStcd = "A"
                cfmMsg = reptTme + "회차 취업활동계획 이행 보고서를 저장하시겠습니까?";
            } else {

                if(!f_payApplValidChk()){
                    return false;
                }

                reptStcd = "B"
                cfmMsg = "제출하면 수정이 불가능합니다.\n" + reptTme + "회차 취업활동계획 이행 보고서를 제출 및 구촉수당을 신청하시겠습니까?";
            }

            if (confirm(cfmMsg)) {

                f_loadingBarTimer();


                initFileInput();

                $("#execRptFrm > input[name='reptStcd']").val(reptStcd);
                $("#execRptFrm > input[name='saveGbn']").val(saveGbn);
                $("#execRptFrm > input[name='payStcd']").val("Z");

                $("#execRptFrm").prop("action", "/kua/mypage/uploadFileKuaEmpAcvtExecRptRegister.do" + "?_csrf=" + $("meta[name='_csrf']").prop("content")).submit();
            }
        }
    });

};
