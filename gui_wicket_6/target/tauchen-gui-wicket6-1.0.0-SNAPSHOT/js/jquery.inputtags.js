(function(c) {
	var a = new Array();
	var b = new Array();
	c.fn.addTag = function(f, e) {
		var e = jQuery.extend({
			focus : false,
			callback : true
		}, e);
		this.each(function() {
			id = c(this).attr("id");
			var g = c(this).val().split(a[id]);
			if (g[0] == "") {
				g = new Array()
			}
			f = jQuery.trim(f);
			if (e.unique) {
				skipTag = c(g).tagExist(f)
			} else {
				skipTag = false
			}
			if (f != "" && skipTag != true) {
				c("<span>").addClass("tag").append(
						c("<span>").text(f).append("&nbsp;&nbsp;"), c("<a>", {
							href : "#",
							title : "Removing tag",
							text : "x"
						}).click(function() {
							return c("#" + id).removeTag(escape(f))
						})).insertBefore("#" + id + "_addTag");
				g.push(f);
				c("#" + id + "_tag").val("");
				if (e.focus) {
					c("#" + id + "_tag").focus()
				} else {
					c("#" + id + "_tag").blur()
				}
				if (e.callback && b[id] && b[id]["onAddTag"]) {
					var j = b[id]["onAddTag"];
					j(f)
				}
				if (b[id] && b[id]["onChange"]) {
					var h = g.length;
					var j = b[id]["onChange"];
					j(c(this), g[h])
				}
			}
			c.fn.tagsInput.updateTagsField(this, g)
		});
		return false
	};
	c.fn.removeTag = function(e) {
		e = unescape(e);
		this.each(function() {
			id = c(this).attr("id");
			var g = c(this).val().split(a[id]);
			c("#" + id + "_tagsinput .tag").remove();
			str = "";
			for (i = 0; i < g.length; i++) {
				if (g[i] != e) {
					str = str + a[id] + g[i]
				}
			}
			c.fn.tagsInput.importTags(this, str);
			if (b[id] && b[id]["onRemoveTag"]) {
				var h = b[id]["onRemoveTag"];
				h(e)
			}
		});
		return false
	};
	c.fn.tagExist = function(e) {
		if (jQuery.inArray(e, c(this)) == -1) {
			return false
		} else {
			return true
		}
	};
	c.fn.importTags = function(e) {
		c("#" + id + "_tagsinput .tag").remove();
		c.fn.tagsInput.importTags(this, e)
	};
	c.fn.tagsInput = function(e) {
		var f = jQuery.extend({
			interactive : true,
			defaultText : "add a tag",
			minChars : 0,
			width : "300px",
			height : "100px",
			autocomplete : {
				selectFirst : false
			},
			hide : true,
			delimiter : ",",
			unique : true,
			removeWithBackspace : true,
			placeholderColor : "#666666"
		}, e);
		this
				.each(function() {
					if (f.hide) {
						c(this).hide()
					}
					id = c(this).attr("id");
					data = jQuery.extend({
						pid : id,
						real_input : "#" + id,
						holder : "#" + id + "_tagsinput",
						input_wrapper : "#" + id + "_addTag",
						fake_input : "#" + id + "_tag"
					}, f);
					a[id] = data.delimiter;
					if (f.onAddTag || f.onRemoveTag || f.onChange) {
						b[id] = new Array();
						b[id]["onAddTag"] = f.onAddTag;
						b[id]["onRemoveTag"] = f.onRemoveTag;
						b[id]["onChange"] = f.onChange
					}
					var g = '<div id="' + id
							+ '_tagsinput" class="tagsinput"><div id="' + id
							+ '_addTag">';
					if (f.interactive) {
						g = g + '<input id="' + id
								+ '_tag" value="" data-default="'
								+ f.defaultText + '" />'
					}
					g = g + '</div><div class="tags_clear"></div></div>';
					c(g).insertAfter(this);
					c(data.holder).css("width", f.width);
					c(data.holder).css("height", f.height);
					if (c(data.real_input).val() != "") {
						c.fn.tagsInput.importTags(c(data.real_input), c(
								data.real_input).val())
					}
					if (f.interactive) {
						c(data.fake_input).val(
								c(data.fake_input).attr("data-default"));
						c(data.fake_input).css("color", f.placeholderColor);
						c(data.holder).bind("click", data, function(h) {
							c(h.data.fake_input).focus()
						});
						c(data.fake_input).bind(
								"focus",
								data,
								function(h) {
									if (c(h.data.fake_input).val() == c(
											h.data.fake_input).attr(
											"data-default")) {
										c(h.data.fake_input).val("")
									}
									c(h.data.fake_input)
											.css("color", "#000000")
								});
						if (f.autocomplete_url != undefined) {
							autocomplete_options = {
								source : f.autocomplete_url
							};
							for (attrname in f.autocomplete) {
								autocomplete_options[attrname] = f.autocomplete[attrname]
							}
							if (jQuery.Autocompleter !== undefined) {
								c(data.fake_input).autocomplete(
										f.autocomplete_url, f.autocomplete);
								c(data.fake_input).bind(
										"result",
										data,
										function(h, k, j) {
											if (k) {
												d = k + "";
												c(h.data.real_input).addTag(d,
														{
															focus : true,
															unique : (f.unique)
														})
											}
										})
							} else {
								if (jQuery.ui.autocomplete !== undefined) {
									c(data.fake_input).autocomplete(
											autocomplete_options);
									c(data.fake_input).bind(
											"autocompleteselect",
											data,
											function(h, j) {
												c(h.data.real_input).addTag(
														j.item.value, {
															focus : true,
															unique : (f.unique)
														});
												return false
											})
								}
							}
						} else {
							c(data.fake_input)
									.bind(
											"blur",
											data,
											function(h) {
												var j = c(this).attr(
														"data-default");
												if (c(h.data.fake_input).val() != ""
														&& c(h.data.fake_input)
																.val() != j) {
													if ((h.data.minChars <= c(
															h.data.fake_input)
															.val().length)
															&& (!h.data.maxChars || (h.data.maxChars >= c(
																	h.data.fake_input)
																	.val().length))) {
														c(h.data.real_input)
																.addTag(
																		c(
																				h.data.fake_input)
																				.val(),
																		{
																			focus : true,
																			unique : (f.unique)
																		})
													}
												} else {
													c(h.data.fake_input)
															.val(
																	c(
																			h.data.fake_input)
																			.attr(
																					"data-default"));
													c(h.data.fake_input).css(
															"color",
															f.placeholderColor)
												}
												return false
											})
						}
						c(data.fake_input)
								.bind(
										"keypress",
										data,
										function(h) {
											if (h.which == h.data.delimiter
													.charCodeAt(0)
													|| h.which == 13) {
												if ((h.data.minChars <= c(
														h.data.fake_input)
														.val().length)
														&& (!h.data.maxChars || (h.data.maxChars >= c(
																h.data.fake_input)
																.val().length))) {
													c(h.data.real_input)
															.addTag(
																	c(
																			h.data.fake_input)
																			.val(),
																	{
																		focus : true,
																		unique : (f.unique)
																	})
												}
												return false
											}
										});
						data.removeWithBackspace
								&& c(data.fake_input)
										.bind(
												"keydown",
												function(j) {
													if (j.keyCode == 8
															&& c(this).val() == "") {
														j.preventDefault();
														var h = c(this)
																.closest(
																		".tagsinput")
																.find(
																		".tag:last")
																.text();
														var k = c(this).attr(
																"id").replace(
																/_tag$/, "");
														h = h.replace(
																/[\s]+x$/, "");
														c("#" + k).removeTag(
																escape(h));
														c(this)
																.trigger(
																		"focus")
													}
												});
						c(data.fake_input).blur()
					}
					return false
				});
		return this
	};
	c.fn.tagsInput.updateTagsField = function(f, e) {
		id = c(f).attr("id");
		c(f).val(e.join(a[id]))
	};
	c.fn.tagsInput.importTags = function(h, j) {
		c(h).val("");
		id = c(h).attr("id");
		var e = j.split(a[id]);
		for (i = 0; i < e.length; i++) {
			c(h).addTag(e[i], {
				focus : false,
				callback : false
			})
		}
		if (b[id] && b[id]["onChange"]) {
			var g = b[id]["onChange"];
			g(h, e[i])
		}
	}
})(jQuery);