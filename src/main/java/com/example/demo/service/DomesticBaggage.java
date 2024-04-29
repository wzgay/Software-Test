package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.dao.Info;
@Service
public class DomesticBaggage {
    //计算国内行李托运额
    public double calculateDomesticBaggageBllowance(Info info){
        double[] weight_class = calculate_weight_class(info);
        //获取会员类型
        /*
        1:凤凰知音终身白金卡、白金卡
        2:凤凰知音金卡、银卡
        3:星空联盟金卡
        4:普通会员
         */
        //这里假设经济舱普通票价为500 用以计算行李超重时需要计算的金额
        double yuan_pre_kilogram = 500 * 0.015;
        int membershipType = info.getMembershipType();
        double baggage_in_free_price = calculate_baggage_in_free(info, weight_class[0], membershipType, yuan_pre_kilogram);
        double baggage_not_in_free_price = yuan_pre_kilogram * weight_class[1];
        return baggage_in_free_price + baggage_not_in_free_price;
    }
    //计算纳入免费托运行李额的托运费
    public double calculate_baggage_in_free(Info info, double weight, int membershipType, double yuan_pre_kilogram) {
        double baggage_price = 0;
        //处理普通行李类型
        //成人/儿童票
        if (info.getTravellerType() == 1) {
            if (info.getCockpitType() == 1) {
                //头等舱
                if (membershipType == 1) {//凤凰知音终身白金卡、白金卡
                    if (weight <= 70) {
                        return 0.0;
                    } else {
                        baggage_price = yuan_pre_kilogram * (weight - 70);
                    }
                } else if (membershipType == 2 || membershipType == 3) {//凤凰知音金卡、银卡/星空联盟金卡
                    if (weight <= 60) {
                        return 0.0;
                    } else {
                        baggage_price = yuan_pre_kilogram * (weight - 60);
                    }
                } else {//普通会员
                    if (weight <= 40) {
                        return 0.0;
                    } else {
                        baggage_price = yuan_pre_kilogram * (weight - 40);
                    }
                }
            } else if (info.getCockpitType() == 2) {
                //公务舱
                if (membershipType == 1) {//凤凰知音终身白金卡、白金卡
                    if (weight <= 60) {
                        return 0.0;
                    } else {
                        baggage_price = yuan_pre_kilogram * (weight - 60);
                    }
                } else if (membershipType == 2 || membershipType == 3) {//凤凰知音金卡、银卡/星空联盟金卡
                    if (weight <= 50) {
                        return 0.0;
                    } else {
                        baggage_price = yuan_pre_kilogram * (weight - 50);
                    }
                } else {//普通会员
                    if (weight <= 30) {
                        return 0.0;
                    } else {
                        baggage_price = yuan_pre_kilogram * (weight - 30);
                    }
                }
            } else {
                //悦享经济舱/超级经济舱/经济舱
                if (membershipType == 1) {//凤凰知音终身白金卡、白金卡
                    if (weight <= 50) {
                        return 0.0;
                    } else {
                        baggage_price = yuan_pre_kilogram * (weight - 50);
                    }
                } else if (membershipType == 2 || membershipType == 3) {//凤凰知音金卡、银卡/星空联盟金卡
                    if (weight <= 40) {
                        return 0.0;
                    } else {
                        baggage_price = yuan_pre_kilogram * (weight - 40);
                    }
                } else {//普通会员
                    if (weight <= 20) {
                        return 0.0;
                    } else {
                        baggage_price = yuan_pre_kilogram * (weight - 20);
                    }
                }
            }
        } else {//婴儿票
            //凤凰知音终身白金卡，白金卡
            if (membershipType == 1) {
                if (weight <= 40) {
                    return 0.0;
                } else {
                    baggage_price = yuan_pre_kilogram * (weight - 40);
                }
            } else if (membershipType == 2 || membershipType == 3) {
                //凤凰知音金卡，银卡/星空联盟金卡
                if (weight <= 30) {
                    return 0.0;
                } else {
                    baggage_price = yuan_pre_kilogram * (weight - 30);
                }
            } else {
                //普通会员
                if (weight <= 10) {
                    return 0.0;
                } else {
                    baggage_price = yuan_pre_kilogram * (weight - 10);
                }
            }
        }
        return baggage_price;

    }
    private double[] calculate_weight_class(Info info) {
        double weight1 = info.getWeight1();
        Integer[] baggage1_type = info.getBaggage1Type();
        double weight2 = info.getWeight2();
        Integer[] baggage2_type = info.getBaggage2Type();
        double weight3 = info.getWeight3();
        Integer[] baggage3_type = info.getBaggage3Type();
        //weight_class[0]:计入免费行李额的重量
        //weight_class[1]:按照实际行李重量收费
        double[] weight_class = {0, 0};
        //通过行李类别计算纳入免费行李额的重量
        if(baggage1_type.length > 0){
        calculate_weight_by_class(baggage1_type, weight1, weight_class);
        }
        if(baggage2_type.length > 0) {
            calculate_weight_by_class(baggage2_type, weight2, weight_class);
        }
        if(baggage3_type.length > 0) {
            calculate_weight_by_class(baggage3_type, weight3, weight_class);
        }
        return weight_class;
    }
    //根据行李类型计算纳入免费托运行李额的重量和按照实际重量收费的重量
    private double[] calculate_weight_by_class(Integer[] baggage_type,double weight, double[] weight_class) {
        //1.普通行李，纳入免费托运范例
        if(baggage_type[0] == 0){
            weight_class[0] += weight;
            return weight_class;
        }
        //2.特殊行李
        //2.1.特殊行李第1类属于可免费运输的特殊行李
        if(baggage_type[1] == 1) return weight_class;
        //2.2特殊行李：运动机械器具
        if(baggage_type[1] == 2){
            //运动器械第1和第3类纳入免费托运范例
            if(baggage_type[2] == 1 || baggage_type[2] == 3) {
                weight_class[0] += weight;
                return weight_class;
            }else{
                //运动器械第2类按实际重量收费
                weight_class[1] += weight;
                return weight_class;
            }
        }
        //2.3其他类
        //2.3.1 其他类中第一类纳入免费托运范例
        if(baggage_type[1] == 3){
            //其他类第1类纳入免费托运范例
            if(baggage_type[2] == 1) {
                weight_class[0] += weight;
                return weight_class;
            }else{
                weight_class[1] += weight;
                return weight_class;
            }
        }
        return weight_class;
    }
}
