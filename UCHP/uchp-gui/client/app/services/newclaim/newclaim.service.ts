/// <reference path='../../_all.ts' />

module uchpClientAngularApp {
  'use strict';

  export class NewClaimService implements INewClaimService {
    private scaffold: any;
    private readyObject: Object = {};
    static $inject = [
      '$log',
      'DateUtilService'
    ];

    constructor(private $log: ng.ILogService,
                private DateUtilService: IDateUtilService
    ) {
      this.$log.debug('Initializing NewClaimService...');

      this.scaffold = {
        activeJobHasErrors: !1,
        activeJobIdFromVerticalTab: '',
        activeTab: '',
        allMINames: [
          {
            description: '',
            disabled: !1,
            id: 0,
            miType: 0,
            name: '',
            tmaSiteId: ''
          }
        ],
        allowCopyClaimJob: !1,
        appealStateList: [
          {
            displayText: '',
            key: ''
          }
        ],
        applyVATBasedOnMaterial: !1,
        assessmentCostType: 0,
        assessmentMode: '',
        campaign: {
          anyMaterialAllowed: !1,
          campaignId: 0,
          campaignState: 0,
          campaignStateTime: '',
          campOwner: 0,
          causalPartList: [
            {
              campaignCausalPartId: 0,
              causalPartDesc: '',
              causalPartNo: '',
              causalPartNoPrefix: '',
              cmpCampaignId: 0
            }
          ],
          companyNo: 0,
          currency: '',
          debitCode: 0,
          defectCode: '',
          forceTSU: !1,
          forceTSUComment: '',
          forceTSUModifiedBy: '',
          forceTSUModifyTime: '',
          functionGroup: '',
          hasVehicleSelection: !1,
          impRegionUsedToMatchClaimJob: !1,
          labourList: [
            {
              campaignLabourId: 0,
              cmpCampaignId: 0,
              convKitNo: '',
              convKitPrefix: '',
              description: '',
              hours: 0,
              operationId: '',
              operationNo: '',
              operationType: 0,
              optional: !1,
              quantity: 0,
              repairFrequency: 0,
              vehicleFrequency: 0,
              vst: !1
            }
          ],
          lastClaimSubmDate: '',
          lastRepairDate: '',
          localCampaign: !1,
          materialList: [
            {
              campaignMaterialId: 0,
              cmpCampaignId: 0,
              description: '',
              optional: !1,
              partNo: '',
              partNoPrefix: '',
              quantity: 0,
              repairFrequency: 0,
              unit: '',
              validation: !1,
              vehicleFrequency: 0
            }
          ],
          materialRegressCode: '',
          modifiedBy: '',
          modifyTime: '',
          otherCostList: [
            {
              campaignCostId: 0,
              cmpCampaignId: 0,
              description: '',
              optional: !1,
              orgCost: 0,
              repairFrequency: 0,
              vehicleFrequency: 0
            }
          ],
          problemDescription: '',
          problemSolvComment: '',
          problemSolved: 0,
          responsible: '',
          sccCode: '',
          sccType: '',
          stairsId: 0,
          startDate: '',
          suggestedAction: '',
          title: '',
          vehicleList: [
            {
              bookedByDealer: '',
              bookedByImporterNo: 0,
              bookedUntilDate: '',
              campaignVehicleInfoId: 0,
              chassisNo: '',
              chassisSeries: '',
              cmpCampaignId: 0,
              customerNo: '',
              isExcluded: !1,
              repairDate: '',
              repairingDealer: '',
              repairingImporterNo: 0,
              responsibleDealer: '',
              responsibleImporterNo: 0,
              softwareUpdByDealer: '',
              softwareUpdByImporterNo: 0,
              softwareUpdDate: '',
              uploadNo: 0
            }
          ],
          vehMaxAge: 0,
          vehMinAge: 0,
          vehTypeMaxMileage: 0,
          vehTypeMaxOperationHours: 0,
          vehTypeMinMileage: 0,
          vehTypeMinOperationHours: 0,
          vptContrLabourCost: 0,
          vptContrLabourPerc: 0,
          vptContrMtrlCost: 0,
          vptContrMtrlPerc: 0,
          vptContrOtherCost: 0,
          vptContrOtherCostPerc: 0
        },
        casenoSourceList: [
          {
            'new': !1,
            caseNoSourceId: '',
            changed: !1,
            clean: !1,
            code: '',
            companyNo: 0,
            deleted: !1,
            description: '',
            disabled: !1,
            modifiedBy: '',
            modifyTime: '',
            name: '',
            preferredDescription: '',
            translatedDescriptionList: [
              {
                countryCode: '',
                description: '',
                inEntity: !1,
                languageCode: '',
                parentCaseNoSourceId: ''
              }
            ],
            typeOfConcern: 0,
            version: 0
          }
        ],
        claimChanged: !1,
        claimJobInfoTabLoaded: !1,
        claimJobTypes: [
          {
            displayText: '',
            key: ''
          }
        ],
        claimRepairHeaderDTO: {
          activeClaimJobDTO: {
            activeTMADecision: 0,
            agePart: 0,
            assessmentDataDTO: {
              coverageDescription: '',
              coverageId: '',
              debitCodeApplied: !1,
              debitCodeCategory: '',
              debitCodeType: '',
              description: '',
              mainDebitCode: 0,
              totalNetCost: 0,
              typeOfConcernText: ''
            },
            autoEvalExceptionTexts: [
              {
                autoEvalExceptId: '',
                autoEvalReqId: 0,
                description: ''
              }
            ],
            autoFailReason: 0,
            availableInShipping: !1,
            caseSourceAllowed: !1,
            causalPart: {
              approvedMaterialNetCost: 0,
              claimMaterialId: 0,
              costAdjustFactor: 0,
              defectCode: '',
              defectCodeDescription: '',
              deletedInGUI: !1,
              designation: '',
              exchangePart: !1,
              functionGroup: '',
              jobClaimJobId: 0,
              jobHeadCompanyNo: 0,
              materialDebitList: [
                {
                  claimMaterialDebitId: '',
                  colNo: 0,
                  coverageId: '',
                  debitCode: 0,
                  debitCodeCategory: 0,
                  debitCodeType: 0,
                  materialClaimMaterialId: 0,
                  modifiedBy: '',
                  modifyTime: '',
                  netCost: 0,
                  netCostImporter: 0,
                  rejectCode: ''
                }
              ],
              materialDescr: '',
              materialNetCost: 0,
              materialNetCostOriginal: 0,
              materialType: 0,
              modifiedBy: '',
              modifyTime: '',
              partMarkup: 0,
              partNo: '',
              partNoPrefix: '',
              priceSource: 0,
              quantity: 0,
              rejectCode: '',
              rejectCodeDescription: '',
              rejectedMaterialNetCost: 0,
              repaired: !1,
              serialNo: '',
              supersededPartNo: '',
              supersededPartNoPrefix: '',
              unitPrice: 0
            },
            claimAssessmentDTO: {
              centralClaimDebitDTO: {
                allDebitCodesEmpty: !1,
                costAdjustmentFactor: 0,
                costDebitGroup: {
                  costAdjustmentFactor: 0,
                  costCarrierDTOList: [
                    {
                      carrierNetCost: 0,
                      claimDebitDTOList: [
                        {
                          amount: 0,
                          colNo: 0,
                          coverageDescription: '',
                          coverageId: '',
                          debitCode: 0,
                          debitCodeCategory: 0,
                          debitCodeType: 0,
                          debitId: '',
                          grandNetTotal: '',
                          hours: 0,
                          percentage: 0,
                          quantity: 0,
                          rejectCode: ''
                        }
                      ],
                      costAdjustmentFactor: 0,
                      costCarrierId: 0,
                      costType: 0,
                      description: '',
                      exchangePart: !1,
                      lastIndex: 0,
                      rejectedInTMA: !1,
                      totalHoursQuantity: 0
                    }
                  ],
                  costDivisionDTOList: [
                    {
                      amount: 0,
                      colNo: 0,
                      debitCode: 0,
                      debitCodeCategory: 0,
                      debitCodeType: 0,
                      hours: 0,
                      percentage: 0,
                      quantity: 0,
                      rejectCode: ''
                    }
                  ],
                  costType: 0,
                  lastIndex: 0,
                  netAmount: 0,
                  totalHoursQuantity: 0
                },
                costDivisionDTOList: [
                  {
                    amount: 0,
                    colNo: 0,
                    debitCode: 0,
                    debitCodeCategory: 0,
                    debitCodeType: 0,
                    hours: 0,
                    percentage: 0,
                    quantity: 0,
                    rejectCode: ''
                  }
                ],
                costUnit: 0,
                grandNetTotal: 0,
                labourDebitGroup: {
                  costAdjustmentFactor: 0,
                  costCarrierDTOList: [
                    {
                      carrierNetCost: 0,
                      claimDebitDTOList: [
                        {
                          amount: 0,
                          colNo: 0,
                          coverageDescription: '',
                          coverageId: '',
                          debitCode: 0,
                          debitCodeCategory: 0,
                          debitCodeType: 0,
                          debitId: '',
                          grandNetTotal: '',
                          hours: 0,
                          percentage: 0,
                          quantity: 0,
                          rejectCode: ''
                        }
                      ],
                      costAdjustmentFactor: 0,
                      costCarrierId: 0,
                      costType: 0,
                      description: '',
                      exchangePart: !1,
                      lastIndex: 0,
                      rejectedInTMA: !1,
                      totalHoursQuantity: 0
                    }
                  ],
                  costDivisionDTOList: [
                    {
                      amount: 0,
                      colNo: 0,
                      debitCode: 0,
                      debitCodeCategory: 0,
                      debitCodeType: 0,
                      hours: 0,
                      percentage: 0,
                      quantity: 0,
                      rejectCode: ''
                    }
                  ],
                  costType: 0,
                  lastIndex: 0,
                  netAmount: 0,
                  totalHoursQuantity: 0
                },
                lastIndex: 0,
                mainCoverageId: '',
                materialDebitGroup: {
                  costAdjustmentFactor: 0,
                  costCarrierDTOList: [
                    {
                      carrierNetCost: 0,
                      claimDebitDTOList: [
                        {
                          amount: 0,
                          colNo: 0,
                          coverageDescription: '',
                          coverageId: '',
                          debitCode: 0,
                          debitCodeCategory: 0,
                          debitCodeType: 0,
                          debitId: '',
                          grandNetTotal: '',
                          hours: 0,
                          percentage: 0,
                          quantity: 0,
                          rejectCode: ''
                        }
                      ],
                      costAdjustmentFactor: 0,
                      costCarrierId: 0,
                      costType: 0,
                      description: '',
                      exchangePart: !1,
                      lastIndex: 0,
                      rejectedInTMA: !1,
                      totalHoursQuantity: 0
                    }
                  ],
                  costDivisionDTOList: [
                    {
                      amount: 0,
                      colNo: 0,
                      debitCode: 0,
                      debitCodeCategory: 0,
                      debitCodeType: 0,
                      hours: 0,
                      percentage: 0,
                      quantity: 0,
                      rejectCode: ''
                    }
                  ],
                  costType: 0,
                  lastIndex: 0,
                  netAmount: 0,
                  totalHoursQuantity: 0
                },
                travelDebitGroup: {
                  costAdjustmentFactor: 0,
                  costCarrierDTOList: [
                    {
                      carrierNetCost: 0,
                      claimDebitDTOList: [
                        {
                          amount: 0,
                          colNo: 0,
                          coverageDescription: '',
                          coverageId: '',
                          debitCode: 0,
                          debitCodeCategory: 0,
                          debitCodeType: 0,
                          debitId: '',
                          grandNetTotal: '',
                          hours: 0,
                          percentage: 0,
                          quantity: 0,
                          rejectCode: ''
                        }
                      ],
                      costAdjustmentFactor: 0,
                      costCarrierId: 0,
                      costType: 0,
                      description: '',
                      exchangePart: !1,
                      lastIndex: 0,
                      rejectedInTMA: !1,
                      totalHoursQuantity: 0
                    }
                  ],
                  costDivisionDTOList: [
                    {
                      amount: 0,
                      colNo: 0,
                      debitCode: 0,
                      debitCodeCategory: 0,
                      debitCodeType: 0,
                      hours: 0,
                      percentage: 0,
                      quantity: 0,
                      rejectCode: ''
                    }
                  ],
                  costType: 0,
                  lastIndex: 0,
                  netAmount: 0,
                  totalHoursQuantity: 0
                }
              },
              debitCodeSummaryDTOList: [
                {
                  costSum: 0,
                  coverageDescription: '',
                  coverageId: '',
                  debitCode: 0,
                  grandNetTotal: 0,
                  labourSum: 0,
                  materialSum: 0,
                  travelSum: 0
                }
              ]
            },
            claimDebitSummariesDTO: {
              approvedTotal: 0,
              claimCostDebitSummaryDTO: {
                approveNetCostTotal: 0,
                hasMoreRejectCodes: !1,
                netCostTotal: 0,
                rejectCode: '',
                rejectDescription: '',
                rejectNetCostTotal: 0
              },
              claimLabourDebitSummaryDTO: {
                approveNetCostTotal: 0,
                hasMoreRejectCodes: !1,
                hours: 0,
                netCostTotal: 0,
                rejectCode: '',
                rejectDescription: '',
                rejectNetCostTotal: 0
              },
              claimMaterialDebitSummaryDTO: {
                approveNetCostTotal: 0,
                hasMoreRejectCodes: !1,
                netCostTotal: 0,
                rejectCode: '',
                rejectDescription: '',
                rejectNetCostTotal: 0
              },
              claimTravelDebitSummaryDTO: {
                approveNetCostTotal: 0,
                hasMoreRejectCodes: !1,
                netCostTotal: 0,
                rejectCode: '',
                rejectDescription: '',
                rejectNetCostTotal: 0
              },
              grandTotal: 0,
              rejectedTotal: 0
            },
            claimJobId: 0,
            claimJobLogDocId: '',
            claimJobState: 0,
            claimValidationOccId: '',
            costList: [
              {
                amountChanged: !1,
                approvedCostNetCost: 0,
                claimCostId: 0,
                core: !1,
                costAdjustFactor: 0,
                costDebitList: [
                  {
                    claimCostDebitId: '',
                    colNo: 0,
                    costClaimCostId: 0,
                    coverageId: '',
                    debitCode: 0,
                    debitCodeCategory: 0,
                    debitCodeType: 0,
                    modifiedBy: '',
                    modifyTime: '',
                    netCost: 0,
                    rejectCode: ''
                  }
                ],
                deletedInGUI: !1,
                description: '',
                jobClaimJobId: 0,
                netCost: 0,
                netCostOriginal: 0,
                otherCostCategoryCode: 0,
                quantity: 0,
                rejectCode: '',
                rejectCodeDescription: '',
                rejectedCostNetCost: 0,
                unitPrice: 0
              }
            ],
            costTransactionSummaryDTO: {
              currency: '',
              dealerCostTransactionList: [
                {
                  claimJobId: 0,
                  creditDocumentId: '',
                  creditingDate: '',
                  creditingTypeId: 0,
                  currency: '',
                  debitCode: 0,
                  debitCodeCategory: 0,
                  debitCodeType: 0,
                  labourAmount: 0,
                  materialAmount: 0,
                  otherCostAmount: 0,
                  totalNetAmount: 0,
                  travelAmount: 0
                }
              ],
              dealerLabourNetTotal: 0,
              dealerMaterialNetTotal: 0,
              dealerOtherCostNetTotal: 0,
              dealerTotalNetTotal: 0,
              dealerTravelNetTotal: 0,
              factoryCostTransactionList: [
                {
                  claimJobId: 0,
                  creditDocumentId: '',
                  creditingDate: '',
                  creditingTypeId: 0,
                  currency: '',
                  debitCode: 0,
                  debitCodeCategory: 0,
                  debitCodeType: 0,
                  labourAmount: 0,
                  materialAmount: 0,
                  otherCostAmount: 0,
                  totalNetAmount: 0,
                  travelAmount: 0
                }
              ],
              factoryLabourNetTotal: 0,
              factoryMaterialNetTotal: 0,
              factoryOtherCostNetTotal: 0,
              factoryTotalNetTotal: 0,
              factoryTravelNetTotal: 0,
              rejectCostTransactionList: [
                {
                  claimJobId: 0,
                  creditDocumentId: '',
                  creditingDate: '',
                  creditingTypeId: 0,
                  currency: '',
                  debitCode: 0,
                  debitCodeCategory: 0,
                  debitCodeType: 0,
                  labourAmount: 0,
                  materialAmount: 0,
                  otherCostAmount: 0,
                  totalNetAmount: 0,
                  travelAmount: 0
                }
              ]
            },
            coverageId: '',
            creditHoldExceptionTexts: [
              {
                creditHoldExceptId: '',
                creditHoldReqId: 0,
                description: '',
                name: ''
              }
            ],
            debitList: [
              {
                amount: 0,
                colNo: 0,
                coverageDescription: '',
                coverageId: '',
                debitCode: 0,
                debitCodeCategory: 0,
                debitCodeType: 0,
                debitId: '',
                grandNetTotal: '',
                hours: 0,
                percentage: 0,
                quantity: 0,
                rejectCode: ''
              }
            ],
            defaultAssessmentView: '',
            errorList: [
              {
                checkCategoryNo: 0,
                checkId: '',
                errorNo: 0,
                errorTxt: '',
                errorTxtParams: '',
                sequenceNo: 0
              }
            ],
            followUpCode: '',
            followUpCodeDescription: '',
            forced: !1,
            grandTotalCost: 0,
            hasBeenInState: !1,
            headCompanyNo: 0,
            headerClaimHeaderId: 0,
            historyList: [
              {
                changeType: 0,
                claimHeaderId: 0,
                claimJobHistoryID: '',
                claimJobID: 0,
                companyNo: 0,
                modifiedBy: '',
                modifyTime: '',
                newValue: 0
              }
            ],
            hours: 0,
            includedMaterialList: [
              {
                approvedMaterialNetCost: 0,
                claimMaterialId: 0,
                costAdjustFactor: 0,
                defectCode: '',
                defectCodeDescription: '',
                deletedInGUI: !1,
                designation: '',
                exchangePart: !1,
                functionGroup: '',
                jobClaimJobId: 0,
                jobHeadCompanyNo: 0,
                materialDebitList: [
                  {
                    claimMaterialDebitId: '',
                    colNo: 0,
                    coverageId: '',
                    debitCode: 0,
                    debitCodeCategory: 0,
                    debitCodeType: 0,
                    materialClaimMaterialId: 0,
                    modifiedBy: '',
                    modifyTime: '',
                    netCost: 0,
                    netCostImporter: 0,
                    rejectCode: ''
                  }
                ],
                materialDescr: '',
                materialNetCost: 0,
                materialNetCostOriginal: 0,
                materialType: 0,
                modifiedBy: '',
                modifyTime: '',
                partMarkup: 0,
                partNo: '',
                partNoPrefix: '',
                priceSource: 0,
                quantity: 0,
                rejectCode: '',
                rejectCodeDescription: '',
                rejectedMaterialNetCost: 0,
                repaired: !1,
                serialNo: '',
                supersededPartNo: '',
                supersededPartNoPrefix: '',
                unitPrice: 0
              }
            ],
            inspectorReportChanged: !1,
            inspectorReportId: 0,
            inspectorReportVehicleId: '',
            jobNo: 0,
            labourList: [
              {
                approvedLabourNetCost: 0,
                claimLabourId: 0,
                costAdjustFactor: 0,
                costAdjustFactorOriginal: 0,
                deletedInGUI: !1,
                description: '',
                headCompanyNo: 0,
                hours: 0,
                jobClaimJobId: 0,
                labourDebitList: [
                  {
                    claimLabourDebitId: '',
                    colNo: 0,
                    coverageId: '',
                    debitCode: 0,
                    debitCodeCategory: 0,
                    debitCodeType: 0,
                    labourClaimLabourId: 0,
                    modifiedBy: '',
                    modifyTime: '',
                    netCost: 0,
                    rejectCode: ''
                  }
                ],
                labourNetCost: 0,
                labourNetCostOriginal: 0,
                labourRate: 0,
                modifiedBy: '',
                modifyTime: '',
                operationId: '',
                operationNo: '',
                operationType: 0,
                quantity: 0,
                rejectCode: '',
                rejectCodeDescription: '',
                rejectedLabourNetCost: 0,
                vst: !1
              }
            ],
            link: !1,
            listOfValidationErrors: [
              {
                checkCategoryNo: 0,
                checkId: '',
                errorNo: 0,
                errorTxt: '',
                errorTxtParams: '',
                sequenceNo: 0
              }
            ],
            logDocList: [
              {
                claimDocId: '',
                claimDocument: [],
                companyNo: 0,
                descendingSortOrder: !1,
                docName: '',
                fetchSize: 0,
                jobClaimJobId: 0,
                numberOfRows: 0,
                orderBy: '',
                startIndex: 0
              }
            ],
            materialInst: 0,
            materialInstDate: '',
            miCode: '',
            miPrintStatus: 0,
            miPrintStatusDate: '',
            miType: 0,
            modifiedBy: '',
            modifyTime: '',
            mtrlReqId: 0,
            newPartDescription: '',
            newPartNo: '',
            noteToDealer: '',
            operationFormat: 0,
            operationId: '',
            operationNo: '',
            operationNoDescription: '',
            overridden: !1,
            partOperatingHours: 0,
            partsFittedDate: '',
            partsMilage: 0,
            pdfLogSize: 0,
            refJobNo: 0,
            refJobNoOwner: '',
            registrationDate: '',
            released: !1,
            remarkCause: '',
            remarkComment: '',
            remarkComplaint: '',
            remarkCorrection: '',
            returnList: [
              {
                active: !1,
                claimReturnId: 0,
                deletedInGUI: !1,
                description: '',
                jobClaimJobId: 0,
                returnCode: ''
              }
            ],
            scc: '',
            sccDescription: '',
            scenarioCode: '',
            scenarioDescription: '',
            selectedMITypeIndex: 0,
            shippingDeliveryDTO: {
              carrierId: '',
              companyNo: 0,
              dealerNo: '',
              importerNo: 0,
              mimeTypeShippingDeliveryDoc: '',
              modifiedBy: '',
              modifyTime: '',
              shippingDeliveryDoc: [],
              shippingDeliveryId: 0,
              shippingDeliveryState: 0,
              tmaSiteId: '',
              trackingId: ''
            },
            showTrackingIdPopup: !1,
            splitPerformed: !1,
            supplierNo: 0,
            tmaSiteId: '',
            travelList: [
              {
                amount: 0,
                approvedTravelNetCost: 0,
                claimTravelId: 0,
                costAdjustFactor: 0,
                deletedInGUI: !1,
                description: '',
                jobClaimJobId: 0,
                modifiedBy: '',
                modifyTime: '',
                rate: 0,
                rejectCode: '',
                rejectCodeDescription: '',
                rejectedTravelNetCost: 0,
                travelNetCost: 0,
                travelNetCostOriginal: 0,
                travelType: 0
              }
            ],
            typeOfClaim: 0,
            typeOfConcern: 0,
            unplannedStop: !1,
            version: 0
          },
          blocked: !1,
          changed: !1,
          claimHeaderId: 0,
          claimJobIDDTOList: [
            {
              claimJobId: 0,
              headClaimHeaderId: 0,
              headCompanyNo: 0,
              jobno: 0
            }
          ],
          companyNo: 1,
          contractNo: 0,
          currency: '',
          dealerList: [
            {
              addressLine1: '',
              addressLine2: '',
              addressLine3: '',
              companyCode: '',
              companyNo: 0,
              countryCode: '',
              dealerClass: '',
              dealerCode: '',
              dealerName: '',
              dealerNo: '',
              dealerType: '',
              id: 0,
              importerNo: 0,
              inactiveCode: '',
              languageCode: '',
              mainDealerNumber: '',
              mainPlace: '',
              name: '',
              vipsDealerNo: ''
            }
          ],
          demoDeliveringAge: 0,
          demoDeliveringDate: '',
          demoVehicle: '',
          exchangeRate: 0,
          fiscalReceiptIssueDate: '',
          fiscalReceiptNumber: '',
          forced: !1,
          inactiveCode: '',
          make: 100,
          makeList: [],
          modifiedBy: '',
          modifyTime: '',
          noVehicle: !0,
          originalVehicleDTO: {
            ageVehicle: 0,
            chassisNo: '',
            chassisSeries: '',
            customerName: '',
            customerNo: '',
            deliveringDate: '',
            deliveringDealer: '',
            deliveringDealerName: '',
            deliveringImporter: 0,
            deliveringImporterName: '',
            make: 0,
            marketType: '',
            mileageUnit: 0,
            mileageUnitEnabled: !1,
            operatingHours: 0,
            operatingMeasureType: 0,
            registrationNo: '',
            repairingDealer: '',
            repairingImporter: 0,
            unitNumber: '',
            vehicleMilage: 0,
            vin: ''
          },
          readOnly: !1,
          refNo: 0,
          registrationDate: '',
          repairDate: '', // '14/12/16'
          repairingDealer: '',
          repairingDealerName: '',
          repairingImporter: 0,
          repairingImporterName: '',
          repairOrderNo: '',
          shippedInvoicedAge: 0,
          shippedInvoicedDate: '',
          updateVehicleInformation: !1,
          vehicleDTO: {
            ageVehicle: 0,
            chassisNo: '',
            chassisSeries: '',
            customerName: '',
            customerNo: '',
            deliveringDate: '',
            deliveringDealer: '',
            deliveringDealerName: '',
            deliveringImporter: 0,
            deliveringImporterName: '',
            make: 0,
            marketType: '',
            mileageUnit: 0,
            mileageUnitEnabled: !1,
            operatingHours: 0,
            operatingMeasureType: 0,
            registrationNo: '',
            repairingDealer: '',
            repairingImporter: 0,
            unitNumber: '',
            vehicleMilage: 0,
            vin: ''
          },
          vehicleOperation: 0,
          vehicleOperationAndDescription: '',
          vehicleOperationDescription: '',
          version: 0
        },
        claimSupplementDTO: {
          'new': !1,
          appealState: 0,
          attachmentList: [
            {
              'new': !1,
              attachment: [],
              attachmentName: '',
              changed: !1,
              claimDocumentId: '',
              claimJobSuppAttId: '',
              claimJobSuppId: 0,
              clean: !1,
              companyNo: 0,
              deleted: !1,
              deletedInGUI: !1,
              mimeType: ''
            }
          ],
          caseNo: '',
          caseSourceCode: '',
          caseSourceId: '',
          changed: !1,
          claimComment: '',
          claimJobId: 0,
          claimJobSuppId: 0,
          clean: !1,
          companyNo: 0,
          deleted: !1,
          expectAttachment: !1,
          historyList: [
            {
              appealState: 0,
              comment: '',
              modifiedBy: '',
              modifyTime: ''
            }
          ],
          internalComment: '',
          modifiedBy: '',
          modifyTime: '',
          version: 0
        },
        claimValidationErrorTextDTOList: [
          {
            checkCategoryNo: 0,
            checkId: '',
            errorNo: 0,
            errorTxt: '',
            errorTxtParams: '',
            sequenceNo: 0
          }
        ],
        companyList: [
          {
            displayText: '',
            key: ''
          }
        ],
        copyClaimJobNO: '',
        copyClaimRefNO: '',
        dbAssessmentDTO: {
          centralClaimDebitDTO: {
            allDebitCodesEmpty: !1,
            costAdjustmentFactor: 0,
            costDebitGroup: {
              costAdjustmentFactor: 0,
              costCarrierDTOList: [
                {
                  carrierNetCost: 0,
                  claimDebitDTOList: [
                    {
                      amount: 0,
                      colNo: 0,
                      coverageDescription: '',
                      coverageId: '',
                      debitCode: 0,
                      debitCodeCategory: 0,
                      debitCodeType: 0,
                      debitId: '',
                      grandNetTotal: '',
                      hours: 0,
                      percentage: 0,
                      quantity: 0,
                      rejectCode: ''
                    }
                  ],
                  costAdjustmentFactor: 0,
                  costCarrierId: 0,
                  costType: 0,
                  description: '',
                  exchangePart: !1,
                  lastIndex: 0,
                  rejectedInTMA: !1,
                  totalHoursQuantity: 0
                }
              ],
              costDivisionDTOList: [
                {
                  amount: 0,
                  colNo: 0,
                  debitCode: 0,
                  debitCodeCategory: 0,
                  debitCodeType: 0,
                  hours: 0,
                  percentage: 0,
                  quantity: 0,
                  rejectCode: ''
                }
              ],
              costType: 0,
              lastIndex: 0,
              netAmount: 0,
              totalHoursQuantity: 0
            },
            costDivisionDTOList: [
              {
                amount: 0,
                colNo: 0,
                debitCode: 0,
                debitCodeCategory: 0,
                debitCodeType: 0,
                hours: 0,
                percentage: 0,
                quantity: 0,
                rejectCode: ''
              }
            ],
            costUnit: 0,
            grandNetTotal: 0,
            labourDebitGroup: {
              costAdjustmentFactor: 0,
              costCarrierDTOList: [
                {
                  carrierNetCost: 0,
                  claimDebitDTOList: [
                    {
                      amount: 0,
                      colNo: 0,
                      coverageDescription: '',
                      coverageId: '',
                      debitCode: 0,
                      debitCodeCategory: 0,
                      debitCodeType: 0,
                      debitId: '',
                      grandNetTotal: '',
                      hours: 0,
                      percentage: 0,
                      quantity: 0,
                      rejectCode: ''
                    }
                  ],
                  costAdjustmentFactor: 0,
                  costCarrierId: 0,
                  costType: 0,
                  description: '',
                  exchangePart: !1,
                  lastIndex: 0,
                  rejectedInTMA: !1,
                  totalHoursQuantity: 0
                }
              ],
              costDivisionDTOList: [
                {
                  amount: 0,
                  colNo: 0,
                  debitCode: 0,
                  debitCodeCategory: 0,
                  debitCodeType: 0,
                  hours: 0,
                  percentage: 0,
                  quantity: 0,
                  rejectCode: ''
                }
              ],
              costType: 0,
              lastIndex: 0,
              netAmount: 0,
              totalHoursQuantity: 0
            },
            lastIndex: 0,
            mainCoverageId: '',
            materialDebitGroup: {
              costAdjustmentFactor: 0,
              costCarrierDTOList: [
                {
                  carrierNetCost: 0,
                  claimDebitDTOList: [
                    {
                      amount: 0,
                      colNo: 0,
                      coverageDescription: '',
                      coverageId: '',
                      debitCode: 0,
                      debitCodeCategory: 0,
                      debitCodeType: 0,
                      debitId: '',
                      grandNetTotal: '',
                      hours: 0,
                      percentage: 0,
                      quantity: 0,
                      rejectCode: ''
                    }
                  ],
                  costAdjustmentFactor: 0,
                  costCarrierId: 0,
                  costType: 0,
                  description: '',
                  exchangePart: !1,
                  lastIndex: 0,
                  rejectedInTMA: !1,
                  totalHoursQuantity: 0
                }
              ],
              costDivisionDTOList: [
                {
                  amount: 0,
                  colNo: 0,
                  debitCode: 0,
                  debitCodeCategory: 0,
                  debitCodeType: 0,
                  hours: 0,
                  percentage: 0,
                  quantity: 0,
                  rejectCode: ''
                }
              ],
              costType: 0,
              lastIndex: 0,
              netAmount: 0,
              totalHoursQuantity: 0
            },
            travelDebitGroup: {
              costAdjustmentFactor: 0,
              costCarrierDTOList: [
                {
                  carrierNetCost: 0,
                  claimDebitDTOList: [
                    {
                      amount: 0,
                      colNo: 0,
                      coverageDescription: '',
                      coverageId: '',
                      debitCode: 0,
                      debitCodeCategory: 0,
                      debitCodeType: 0,
                      debitId: '',
                      grandNetTotal: '',
                      hours: 0,
                      percentage: 0,
                      quantity: 0,
                      rejectCode: ''
                    }
                  ],
                  costAdjustmentFactor: 0,
                  costCarrierId: 0,
                  costType: 0,
                  description: '',
                  exchangePart: !1,
                  lastIndex: 0,
                  rejectedInTMA: !1,
                  totalHoursQuantity: 0
                }
              ],
              costDivisionDTOList: [
                {
                  amount: 0,
                  colNo: 0,
                  debitCode: 0,
                  debitCodeCategory: 0,
                  debitCodeType: 0,
                  hours: 0,
                  percentage: 0,
                  quantity: 0,
                  rejectCode: ''
                }
              ],
              costType: 0,
              lastIndex: 0,
              netAmount: 0,
              totalHoursQuantity: 0
            }
          },
          debitCodeSummaryDTOList: [
            {
              costSum: 0,
              coverageDescription: '',
              coverageId: '',
              debitCode: 0,
              grandNetTotal: 0,
              labourSum: 0,
              materialSum: 0,
              travelSum: 0
            }
          ]
        },
        defaultPartNoPrefix: 'VO',
        disableVehicleInfo: !1,
        inspectorReportAndVehicleMap: {},
        inspectorReportStatuses: [
          {
            displayText: '',
            key: ''
          }
        ],
        jobListSize: 0,
        labourTabLoaded: !1,
        makeList: [
          {
            displayText: '',
            key: ''
          }
        ],
        matchedInspectorReport: {
          'new': !1,
          allowAutoprocessing: !1,
          caseNo: '',
          caseNoSourceId: '',
          causalPartNo: '',
          causalPartNoPrefix: '',
          changed: !1,
          chassisNo: '',
          chassisNoMarketType: '',
          chassisSeries: '',
          claimJobFunctionGroup: '',
          claimjobMarketingType: '',
          claimJobMarketType: '',
          clean: !1,
          closeDate: '',
          companyNo: 0,
          costAmount: 0,
          costPercentage: 0,
          currency: '',
          customerName: '',
          customerNo: '',
          dealerNo: '',
          debitCode: 0,
          defectCode: '',
          deleted: !1,
          deliveryDate: '',
          followUpCode: '',
          importerNo: 0,
          inspectorId: '',
          inspectorName: '',
          inspectorReportChanged: !1,
          inspectorReportFunctionGroup: '',
          inspectorReportId: 0,
          inspectorReportVehicleList: [
            {
              'new': !1,
              changed: !1,
              chassisNo: '',
              chassisSeries: '',
              claimJobFunctionGroup: '',
              claimRefNo: '',
              clean: !1,
              dealerNo: '',
              deleted: !1,
              deleteVehicleEnabled: !1,
              deliveryDate: '',
              functionGroup: '',
              importerNo: '',
              inspectorReportVehicleId: '',
              jobNo: 0,
              linkedClaimJobId: 0,
              linkedClaimRepairHeaderId: 0,
              marketingType: '',
              mileage: '',
              modifiedBy: '',
              modifyTime: '',
              refNo: 0,
              repairingDealer: '',
              repairingImporter: 0,
              selectedVehicle: !1,
              used: !1,
              vehicleMileage: 0
            }
          ],
          internalRemarks: '',
          jobNo: 0,
          labourAmount: 0,
          labourPerc: 0,
          linkedClaimHeaderId: 0,
          linkedClaimJobId: 0,
          marketType: '',
          materialAmount: 0,
          materialPerc: 0,
          mileageUnit: 0,
          modifiedBy: '',
          modifyTime: '',
          multipleVehicle: !1,
          refNo: 0,
          registrationDate: '',
          registrationNo: '',
          repairingDealer: '',
          reportMileage: 0,
          reportMileageMiles: 0,
          reportOperatingHours: 0,
          reportStatus: !1,
          reportText: '',
          scc: '',
          totalAmount: 0,
          totalPercentage: 0,
          travelAmount: 0,
          travelPercentage: 0,
          typeOfConcern: 0,
          used: !1,
          vehicleMilage: 0,
          version: 0
        },
        matchedInspectorReportList: [
          {
            'new': !1,
            allowAutoprocessing: !1,
            caseNo: '',
            caseNoSourceId: '',
            causalPartNo: '',
            causalPartNoPrefix: '',
            changed: !1,
            chassisNo: '',
            chassisNoMarketType: '',
            chassisSeries: '',
            claimJobFunctionGroup: '',
            claimjobMarketingType: '',
            claimJobMarketType: '',
            clean: !1,
            closeDate: '',
            companyNo: 0,
            costAmount: 0,
            costPercentage: 0,
            currency: '',
            customerName: '',
            customerNo: '',
            dealerNo: '',
            debitCode: 0,
            defectCode: '',
            deleted: !1,
            deliveryDate: '',
            followUpCode: '',
            importerNo: 0,
            inspectorId: '',
            inspectorName: '',
            inspectorReportChanged: !1,
            inspectorReportFunctionGroup: '',
            inspectorReportId: 0,
            inspectorReportVehicleList: [
              {
                'new': !1,
                changed: !1,
                chassisNo: '',
                chassisSeries: '',
                claimJobFunctionGroup: '',
                claimRefNo: '',
                clean: !1,
                dealerNo: '',
                deleted: !1,
                deleteVehicleEnabled: !1,
                deliveryDate: '',
                functionGroup: '',
                importerNo: '',
                inspectorReportVehicleId: '',
                jobNo: 0,
                linkedClaimJobId: 0,
                linkedClaimRepairHeaderId: 0,
                marketingType: '',
                mileage: '',
                modifiedBy: '',
                modifyTime: '',
                refNo: 0,
                repairingDealer: '',
                repairingImporter: 0,
                selectedVehicle: !1,
                used: !1,
                vehicleMileage: 0
              }
            ],
            internalRemarks: '',
            jobNo: 0,
            labourAmount: 0,
            labourPerc: 0,
            linkedClaimHeaderId: 0,
            linkedClaimJobId: 0,
            marketType: '',
            materialAmount: 0,
            materialPerc: 0,
            mileageUnit: 0,
            modifiedBy: '',
            modifyTime: '',
            multipleVehicle: !1,
            refNo: 0,
            registrationDate: '',
            registrationNo: '',
            repairingDealer: '',
            reportMileage: 0,
            reportMileageMiles: 0,
            reportOperatingHours: 0,
            reportStatus: !1,
            reportText: '',
            scc: '',
            totalAmount: 0,
            totalPercentage: 0,
            travelAmount: 0,
            travelPercentage: 0,
            typeOfConcern: 0,
            used: !1,
            vehicleMilage: 0,
            version: 0
          }
        ],
        mileageUnitList: [
          {
            displayText: '',
            key: ''
          }
        ],
        newClaimJob: !1,
        noChange: !1,
        otherCostCategoryList: [
          {
            displayText: '',
            key: ''
          }
        ],
        popupClaim: !1,
        rejectedDebitCodes: '',
        remarksTabLoaded: !1,
        selectedCausalPartId: '',
        selectedClaimJobs: [
          {
            claimJobId: 0,
            headClaimHeaderId: 0,
            headCompanyNo: 0,
            jobno: 0
          }
        ],
        selectedClaimJobType: '2',
        supplementChanged: !1,
        technicalDataUrl: '',
        technicalDataUrl2: '',
        tmaDecisionInfoPVO: {
          analysisProblem: '',
          analysisResult: '',
          approvalCodeDesc: '',
          approvalCodeTMAId: '',
          claimJobTMAId: 0,
          dealerFeedBack: '',
          faultCode: '',
          faultCodeText: '',
          rejectCode: '',
          rejectCodeDesc: '',
          rejectCodeTMAId: '',
          tmaDecisionId: 0
        },
        uchpCampaignVehicleMaster: !1,
        vehicleOperationAuths: [
          {
            description: '',
            displayText: '',
            vehicleOperationId: 0
          }
        ],
        viewCampaignUrl: '',
        warningFlag: !1,
        workingWithSelectedClaimJobs: !1,
        yesNoList: [
          {
            displayText: '',
            key: ''
          }
        ]
      };
    }

    prepareObject(data) {
      // TODO: numbers? strings??
      this.scaffold.make = +data.make;
      this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO.repairingImporter = +data.repairingImporter;
      this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO.repairingDealer = data.repairingDealer;
      this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO.repairOrderNo = data.repairOrderNo;
      this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO.repairDate = this.DateUtilService.DateParse(data.repairDate);
      this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO.vehicleDTO = data.vehicleDTO;
      // TODO: add more props here
      _.assign(this.readyObject, this.scaffold);
      return this.readyObject;
    }

    addExistingClaim(claimJob) {
      this.scaffold = claimJob;
    }

    addRemarks(data) {
      let remarks = {
        remarkCause: data.remarkCause,
        remarkComplaint: data.remarkComplaint,
        remarkCorrection: data.remarkCorrection
      };
      this.scaffold.claimSupplementDTO.claimComment = data.claimComment;
      this.scaffold.claimSupplementDTO.internalComment = data.internalComment;
      _.assign(this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO, remarks);
    }

    addMaterial(causalPart, includedMaterialList) {
      let material = {
        causalPart: causalPart,
        includedMaterialList: includedMaterialList
      };
      _.assign(this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO, material);
    }

    addCosts(costList) {
      let cost = {
        costList: costList
      };
      _.assign(this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO, cost);
    }

    addLabour(labourList) {
      let labour = {
        labourList: labourList
      };
      _.assign(this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO, labour);
    }

    addHistory(historyList) {
      let history = {
        historyList: historyList
      };
      _.assign(this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO, history);
    }

    addAttachments(attachmentList) {
      let attachments = {
        attachmentList: attachmentList
      };
      _.assign(this.scaffold.claimRepairHeaderDTO.activeClaimJobDTO, attachments);
    }

    get(data) {
      return this.prepareObject(data);
    }
  }

  angular
    .module('uchpClientAngularApp')
    .service('NewClaimService', NewClaimService);
}
