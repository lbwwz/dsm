package com.dsm.controller.user;

import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: lbw
 * Date: 2016/9/5
 * Project: dsm
 */
@Controller
public class AuthenticationController {
//
//
//    private IdentifyInfoDao identifyInfoDao = new IdentifyInfoDaoImpl();
//
//    /**
//     * 提交用户的身份信息（用户验证页面的用户提交）
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @SuppressWarnings("unused")
//    private void submitIdentify(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        ServletFileUpload upload = getServletFileUpload();
//        List<FileItem> items = null;
//        try {
//            // 从 ServletFileUpload 的对象 upload 中解析请求, 得到 FileItem 的集合.
//            items = upload.parseRequest(request);
//            HttpSession session = request.getSession();
//
//            User sessionUser = (User) session.getAttribute("user");
//            int userId = sessionUser.getId();
//            String token = (String) session.getAttribute("idCheckToken");
//
//            if (checkRepeatedSubmit(items, token)) {
//                // 移除标志属性
//                session.removeAttribute("idCheckToken");
//
//                // 根据 items 中的请求类型，分别根据配置文件的设置对请求进行处理，并对文件进行上传操作
//                IdentifyInfo bean = doFileUpload(items);
//
//                System.out.println(bean.toString());
//
//                // 检查文件上传操作是否异常 （bean为空表示上传过程发生异常）
//                if (bean == null)
//                    replyMsg(request, response, "抱歉，身份证号有误或图片文件大小不符合规定！", "/authorityPage/user/idAuthForm.jsp");
//
//                // 返回的bean存入数据库中
//                try {
//                    bean.setUserId(userId);
//                    identifyInfoDao.addIdentifyInfo(bean);
//                } catch (Exception e) {
//                    // 若发生信息插入异常，则删除上传的文件，并向用户反馈相应的提示
//                    e.printStackTrace();
//                    deleteFile(bean.getImageUrl());
//                    replyMsg(request, response, "信息不能为空！", "/authorityPage/user/idAuthForm.jsp");
//                    return;
//                }
//
//                // 删除可能存在的临时文件
//                deleteFile(TEMP_DIR);
//
//                //重置session中的user属性
//                sessionUser.setPromotedType(1);
//                session.setAttribute("user", sessionUser);
//                // 重定向到身份验证主页
//                response.sendRedirect(request.getContextPath() + "/authorityPage/user/idAuthentication.jsp");
//            } else {
//                replyMsg(request, response, "请勿重复提交表单信息！", "/authorityPage/user/idAuthForm.jsp");
//            }
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 用于回复用户一些信息（默认属性名是：“backMsg”）
//     *
//     * @param request
//     * @param response
//     * @param msg
//     * @param replyPath
//     * @throws ServletException
//     * @throws IOException
//     */
//    private void replyMsg(HttpServletRequest request, HttpServletResponse response, String msg, String replyPath)
//            throws ServletException, IOException {
//        request.setAttribute("backMsg", msg);
//        request.getRequestDispatcher(replyPath).forward(request, response);
//    }
//
//    /**
//     * 检查带文件域的表单是否重复提交
//     *
//     * @param items
//     * @param token
//     *            session中的检验参数
//     * @return
//     */
//    private boolean checkRepeatedSubmit(List<FileItem> items, String token) {
//        for (int i = 0; i < items.size(); i++) {
//            FileItem item = items.get(i);
//
//            if (item.isFormField()) {
//                if (item.getFieldName().equals("idCheckToken")) {
//                    try {
//                        return item.getString("UTF-8").equals(token);
//                    } catch (UnsupportedEncodingException e) {
//                    }
//
//                } else {
//                    continue;
//                }
//
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 对获取的FileItem集合进行信息筛选和检测，并将文件上传，返回封装的IdentifyInfo对象
//     *
//     * @param items
//     * @return
//     */
//    private IdentifyInfo doFileUpload(List<FileItem> items) {
//        String realName = null;
//        String idCardNum = null;
//        String idImageUrl = null;
//        System.out.println("size：" + items.size());
//        for (int i = 0; i < items.size(); i++) {
//            FileItem item = items.get(i);
//            //
//            System.out.println(item.getFieldName());
//            // 如果是一般的表单域(获取其属性)
//            if (item.isFormField()) {
//                try {
//                    if (item.getFieldName().equals("realName"))
//                        realName = item.getString("UTF-8").trim();
//                    else if (item.getFieldName().equals("idCardNum"))
//                        idCardNum = item.getString("UTF-8").trim();
//                } catch (UnsupportedEncodingException e) {
//                }
//            }
//            // 如果是文件域
//            else {
//                if (item.getFieldName().equals("idImage")) {
//                    String idImageName = item.getName();
//                    if (checkExtsName(idImageName)) {
//                        // 用文件名产生相应的图片文件路径
//                        idImageUrl = createFilePath(idImageName);
//                        //
//                        System.out.println("idImageUrl:" + idImageUrl);
//                        // 将文件上传到指定的路径
//                        try {
//
//                            uploadFile(idImageUrl, item.getInputStream());
//                        } catch (IOException e) {
//                            System.out.println(idImageName + "上传失败");
//                            e.printStackTrace();
//                            return null;
//                        }
//                    }
//                }
//            }
//        }
//        if(StringCheckUtils.isIdCardNum(idCardNum)){
//            return new IdentifyInfo(realName, idCardNum, idImageUrl);
//        }
//        return null;
//
//    }
//
//    /**
//     * 删除一个文件或者目录
//     *
//     * @param targetPath
//     *            文件或者目录路径
//     * @IOException 当操作发生异常时抛出
//     */
//    private void deleteFile(String targetPath) throws IOException {
//        File targetFile = new File(targetPath);
//
//        // targetPath 不存在，直接返回
//        if (!targetFile.exists())
//            return;
//
//        // 存在
//        if (targetFile.isDirectory()) {
//            FileUtils.deleteDirectory(targetFile);
//        } else if (targetFile.isFile()) {
//            targetFile.delete();
//        }
//    }
//
//    /**
//     * 检查身份验证图片的文件的拓展名是否合法
//     *
//     * @param fileName
//     * @return
//     */
//    private boolean checkExtsName(String fileName) {
//        // 获取配置文件信息
//        String exts = FileUploadAppProperties.getInstance().getProperty("idImage.exts");
//
//        List<String> extNames = Arrays.asList(exts.split(","));
//
//        String theExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
//        if (extNames.contains(theExtName)) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 将文件上传到对应的文件路径
//     *
//     * @param filePath
//     * @param inputStream
//     * @throws IOException
//     */
//    private synchronized void uploadFile(String filePath, InputStream in) throws IOException {
//        OutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
//
//        byte[] bs = new byte[1024];
//        int len = 0;
//        while ((len = in.read(bs)) != -1) {
//            out.write(bs, 0, len);
//        }
//        if (out != null) {
//            out.close();
//        }
//        if (in != null) {
//            in.close();
//        }
//    }
//
//    /**
//     * 根据文件名，产生相应的文件路径
//     * @param fileName
//     * @return
//     */
//    private String createFilePath(String fileName) {
//        String extName = fileName.substring(fileName.lastIndexOf("."));
//        Random random = new Random();
//        StringBuilder filePath = new StringBuilder();
//        filePath.append(getServletContext().getRealPath(SAVE_PATH))
//                .append("\\").append(EncryptionUtils.encryptMD5(fileName+System.currentTimeMillis()))
//                .append(random.nextInt(100000)).append(extName);
//
//        return filePath.toString();
//    }
//
//    /**
//     * 获取 ServletFileUpload 的对象，并对该对象进行参数设置
//     *
//     * @return
//     */
//    private ServletFileUpload getServletFileUpload() {
//        // Create a factory for disk-based file items
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//        String fileMaxSize = FileUploadAppProperties.getInstance().getProperty("headImage.max.size");
//        String totalFileMaxSize = FileUploadAppProperties.getInstance().getProperty("total.headImage.max.size");
//
//        // Set factory 的参数
//        // 设置内存中最多存放文件的大小，超过此大小系统将会把文件以临时文件的方式记录到硬盘上
//        factory.setSizeThreshold(1024 * 1024 * 5);
//        // 设置文件超出内存中可存放文件最大值后将其写入到硬盘中的路径
//        factory.setRepository(createDir(TEMP_DIR));
//
//        // Create a new file upload handler
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        // 设置所有文件的总大小最大是多少
//        upload.setSizeMax(Integer.parseInt(totalFileMaxSize));
//        // 设置单个文件的大小最大是多少
//        upload.setFileSizeMax(Integer.parseInt(fileMaxSize));
//
//        return upload;
//    }
//
//    /**
//     * 创建文件目录，并将其返回
//     *
//     * @return
//     */
//    private File createDir(String dir) {
//        File theDir = new File(dir);
//        if (!theDir.exists()) {
//            theDir.mkdirs();
//        }
//
//        return theDir;
//    }
//
}
