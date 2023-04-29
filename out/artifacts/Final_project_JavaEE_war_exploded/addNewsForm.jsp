<%@ page import="db.NewsCategory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
  <div class="col-12">
    <label>Category:</label>
  </div>
</div>
<div class="row mt-2">
  <div class="col-12">
    <select class="form-control" name="news_category_id">
      <%
        ArrayList<NewsCategory> categories = (ArrayList<NewsCategory>) request.getAttribute("newsCategories");
        if(categories!=null){
          for(NewsCategory newsCategory: categories){
      %>
            <option value="<%=newsCategory.getId()%>"><%=newsCategory.getName()%></option>
      <%
          }
        }
      %>
    </select>
  </div>
</div>
<div class="row mt-3">
  <div class="col-12">
    <label>Title:</label>
  </div>
</div>
<div class="row mt-2">
  <div class="col-12">
    <input type="text" class="form-control" name="news_title" placeholder="Title...">
  </div>
</div>
<div class="row mt-3">
  <div class="col-12">
    <label>Content:</label>
  </div>
</div>
<div class="row mt-2">
  <div class="col-12">
    <textarea type="text" class="form-control" name="news_content" placeholder="Content..." rows="5"></textarea>
  </div>
</div>
