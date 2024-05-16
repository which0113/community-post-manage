<template>
  <section>
    <div class="columns">
      <div class="column is-full">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span><i class="fa fa fa-book"> 主题 / 更新主题</i></span>
          </div>
          <div>
            <el-form :model="topic" ref="topic" class="demo-topic">
              <el-form-item prop="title" :rules="rules.title">
                <el-input
                    v-model="topic.title"
                    placeholder="输入新的主题名称"
                ></el-input>
              </el-form-item>

              <!--Markdown-->
              <div id="vditor"></div>

              <b-taginput
                  v-model="tags"
                  class="my-3"
                  maxlength="15"
                  maxtags="3"
                  ellipsis
                  placeholder="请输入主题标签，限制为 15 个字符和 3 个标签"
              />
              <el-form-item class="mt-3">
                <el-button type="primary" @click="handleUpdate()"
                >更新
                </el-button>
                <el-button @click="resetForm('topic')">重置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </div>
    </div>
  </section>
</template>

<script>
import {getTopic, update} from "@/api/post";
import Vditor from "vditor";
import "vditor/dist/index.css";

export default {
  name: "TopicEdit",
  components: {},
  data() {
    return {
      title: '',
      topic: {},
      tags: [],
      rules: {
        title: [
          {required: true, message: '请输入话题名称', trigger: 'blur'},
          {
            min: 1,
            max: 1024,
            message: '长度在 1 到 1024 个字符',
            trigger: 'blur'
          }
        ]
      }
    };
  },
  created() {
    this.fetchTopic();
  },
  methods: {
    renderMarkdown(md) {
      this.contentEditor = new Vditor("vditor", {
        height: 460,
        placeholder: "输入要更新的内容",
        preview: {
          hljs: {style: "monokai"},
        },
        mode: "sv",
        after: () => {
          this.contentEditor.setValue(md);
        },
      });
    },
    fetchTopic() {
      getTopic(this.$route.params.id).then((value) => {
        this.topic = value.data.topic;
        this.tags = value.data.tags.map(tag => tag.name);
        this.renderMarkdown(this.topic.content);
      });
    },
    handleUpdate: function () {
      if (this.topic.title == null || this.topic.title.length === 0) {
        this.$message.info('话题名称为空')
        return false
      }
      if (
          this.contentEditor.getValue().length === 1 ||
          this.contentEditor.getValue() == null ||
          this.contentEditor.getValue() === ''
      ) {
        this.$message.info('话题内容为空')
        return false
      }
      if (this.tags == null || this.tags.length === 0) {
        this.$message.info('话题标签为空')
        return false
      }
      this.topic.content = this.contentEditor.getValue();
      update(this.topic).then((response) => {
        const {data} = response;
        console.log(data);
        setTimeout(() => {
          this.$message.success('更新成功')
          this.$router.push({
            name: "post-detail",
            params: {id: data.id},
          });
        }, 800);
      });
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.contentEditor.setValue("");
      this.tags = "";
    },
  },
};
</script>

<style>
.vditor-reset pre > code {
  font-size: 100%;
}
</style>
