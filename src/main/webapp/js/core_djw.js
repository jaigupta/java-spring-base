function getcall(url, callback) {
  $.ajax({
    dataType: "json",
    url : url
  }).done(callback);
}

function vote(url, ele) {
  getcall(url, function(data) {
    ele.find(".upvote_count").text(data.upvote_count);
    ele.find(".downvote_count").text(data.downvote_count);
  });
}

function upvote(postId, ele) {
  vote('/post/upvote/' + postId, $(ele).parent());
}

function downvote(postId, ele) {
  vote('/post/downvote/' + postId, $(ele).parent());
}
