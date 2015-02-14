<%@ include file="header.jsp"%>
<div class="container">
	<div class="jumbotron" style="white-space: nowrap;">
		<h2>Query Utility</h2>
		<p>
		<h3>
			Populate the Query to be executed
			<h3>
				<form:form class="form-horizontal" role="form"
					commandName="sqlQuerySearch" action="/jdbcSample/submitQuery.html"
					method="POST">

					<div class="*form-group">
						<label class="control-label col-sm-2" for="email">SQL
							Query:</label>
						<div class="col-sm-10">
							<form:textarea path="sqlQuery" rows="10" cols="50"
								placeholder="SQL Query"></form:textarea>
							<form:errors path="sqlQuery" cssStyle="color: #ff0000;" />
						</div>
					</div>
					<br />
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-2">
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</div>
				</form:form>
				<c:if test="${not empty sqlQuerySearch.headerNames}">
					<div class="panel panel-default">
						<div class="panel-heading">Results</div>
						<!-- Table -->
						<table class="table">
							<thead>
								<tr>
									<c:forEach items="${sqlQuerySearch.headerNames}" var="header">
										<th><c:out value="${header}" /></th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<tr><td>test</td></tr>
							</tbody>
						</table>
					</div>
				</c:if>
	</div>
</div>
<%@ include file="footer.jsp"%>