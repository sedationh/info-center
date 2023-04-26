export const transfromTime = (time) => {
    const date = new Date(time)
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    const hour = date.getHours()
    const min = date.getMinutes()
    const second = date.getSeconds()

    return `${year}-${month}-${day} ${hour}:${min}:${second}`
}
