import request from '@/utils/request'

// 查询包裹列表
export function listParcel(query) {
  return request({
    url: '/system/parcel/list',
    method: 'get',
    params: query
  })
}

// 查询包裹详细
export function getParcel(id) {
  return request({
    url: '/system/parcel/' + id,
    method: 'get'
  })
}

// 新增包裹
export function addParcel(data) {
  return request({
    url: '/system/parcel',
    method: 'post',
    data: data
  })
}

// 修改包裹
export function updateParcel(data) {
  return request({
    url: '/system/parcel',
    method: 'put',
    data: data
  })
}

// 删除包裹
export function delParcel(id) {
  return request({
    url: '/system/parcel/' + id,
    method: 'delete'
  })
}
