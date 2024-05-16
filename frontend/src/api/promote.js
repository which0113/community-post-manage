import request from '@/utils/request'

// 获取友情链接
export function getList() {
  return request(({
    url: '/promotion/all',
    method: 'get'
  }))
}
