import { reactive, ref } from 'vue'
import {isEmail} from '@/utils/validate'
import type { FormInstance,FormRules} from 'element-plus'
const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
interface User {
    username: string
    password: string
    phone: string
    email: string
  }
interface Datum {
    companyInfo: null;
    email: string;
    id: number;
    phone: string;
    username: string;
    userType: null;
    password: string;
    [property: string]: any;
}
export interface bottleds{
  brand: string;
  categoryName: string;
  description: string;
  id: number;
  image: string;
  price: number;
  [property: string]: any;
  imageData:File,
}
export interface deliverys{
    id: number;
    idNumber: null;
    password: string;
    phone: string;
    realName: null;
    username: string;
    [property: string]: any;
}
export interface bottled{ 
 imageData:File,
id: number,
brand:String,
categoryName:String,
price:Number,
description:String,
}
  //扩展效验邮箱
let validateEmail = (rule, value, callback) => {
    if (!isEmail(value)) {
      callback(new Error('邮箱格式错误'))
    } else {
      callback()
    }
  }
  const rules= reactive<FormRules<User>>({
    username:[
      {required:true,message:'未填写用户名',trigger:'blur'}
    ],
    password:[
      {required:true,message:'未填写密码',trigger:'blur'}
    ],
  
  
  })
  const rulesadmin= reactive<FormRules<User>>({
    username:[
      {required:true,message:'未填写用户名',trigger:'blur'}
    ],
    password:[
      {required:true,message:'未填写密码',trigger:'blur'}
    ],
    phone:[
      {required:true,message:'未填写手机号',trigger:'blur'},
      { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }
  
    ],
    email:[
      {required:true,message:'未填写邮箱',trigger:'blur'},
      {validator: validateEmail, trigger: 'blur'}
    ]
  })
  const rulesuser= reactive<FormRules<Datum>>({
    username:[
      {required:true,message:'未填写用户名',trigger:'blur'}
    ],
    password:[
      {required:true,message:'未填写密码',trigger:'blur'}
    ],
    phone:[
      {required:true,message:'未填写手机号',trigger:'blur'},
      { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }
  
    ],
    email:[
      {required:true,message:'未填写邮箱',trigger:'blur'},
      {validator: validateEmail, trigger: 'blur'}
    ]
  })

export {
    formSize,
    ruleFormRef,
    rules,
    rulesadmin,
    rulesuser
}