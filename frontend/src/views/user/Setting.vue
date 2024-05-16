<template>
  <section>
    <el-card shadow="never">
      <div slot="header">
        个人设置
      </div>
      <div class="columns">
        <div class="column is-full">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="基础信息" name="first">
              <el-form ref="ruleForm" :label-position="labelPosition" label-width="100px" :model="user">
                <el-form-item label="账号">
                  <el-input v-model="user.username" disabled/>
                </el-form-item>
                <el-form-item label="昵称" prop="alias" :rules="rules.alias">
                  <el-input v-model="user.alias"/>
                </el-form-item>
                <el-form-item label="简介">
                  <el-input v-model="user.bio"/>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                  <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="头像" name="second">
              <figure class="image is-48x48">
                <img :src="`https://gravatar.kuibu.net/avatar/${this.user.id}?s=164&d=monsterid`">
              </figure>
            </el-tab-pane>
            <el-tab-pane label="电子邮箱" name="third">
              <el-form ref="dynamicValidateForm2" :model="user" label-width="100px" class="demo-dynamic">
                <el-form-item prop="email" :rules="rules.email">
                  <el-input v-model="user.email"/>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm('dynamicValidateForm2')">提交</el-button>
                  <el-button @click="resetForm('dynamicValidateForm2')">重置</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="手机号" name="fourth">
              <el-form ref="dynamicValidateForm3" :model="user" label-width="100px" class="demo-dynamic">
                <el-form-item prop="mobile" :rules="rules.mobile">
                  <el-input v-model="user.mobile"/>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm('dynamicValidateForm3')">提交</el-button>
                  <el-button @click="resetForm('dynamicValidateForm3')">重置</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </el-card>
  </section>
</template>

<script>
import {getInfo, update} from '@/api/user'

export default {
  name: 'Setting',
  data() {
    return {
      activeName: 'first',
      labelPosition: 'right',
      user: {
        id: '',
        username: '',
        alias: '',
        bio: '',
        email: '',
        mobile: '',
        avatar: ''
      },
      rules: {
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              const regex = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
              if (!regex.test(value)) {
                callback(new Error('请输入正确的邮箱地址'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }
        ],
        alias: [
          {required: true, message: '请输入昵称', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              const regex = /^[^\s!@#$%^&*()]+$/;
              if (!regex.test(value)) {
                callback(new Error('请勿包含特殊字符'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }
        ],
        mobile: [
          {message: '请输入手机号', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              if (value.length === 0) {
                callback();
              }
              const regex = /^(|\d+)$/;
              if (!regex.test(value) || value.length < 5) {
                callback(new Error('请输入正确的手机号'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created() {
    this.fetchInfo()
  },
  methods: {
    fetchInfo() {
      getInfo(this.$route.params.username).then(res => {
        console.log(res)
        const {data} = res
        this.user = data
      })
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.user)
          update(this.user).then(res => {
            this.$message.success('信息修改成功')
            this.fetchInfo()
          })
        }
      })
    },
    resetForm(formName) {
      this.fetchInfo()
    }
  }
}
</script>

<style scoped>

</style>
