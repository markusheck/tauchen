var isDirty = false;

function IsPageDirty() {
	if (isDirty) {
		var ignoreChanges = confirm('You have unsaved changes. Proceed anyway?');
		return (ignoreChanges);
	}
	return true;
}

function loadDefaults() {
	
//  $('select.multiselect').multiselect();
	
  $('#searchResultDiv').jScrollPane();	
	
  $('#metaData').jScrollPane();	

  isDirty = false;
  

  $("input, select").change(function() {
	  if ( !$("input").hasClass("visural_dropdown") ) {
		  if (!$(this).hasClass("noCheck")) {	
			  isDirty = true;
		  }
	  }
  });
  
  $("a").not(".always").attr("onclick", "return IsPageDirty();");
  
  /**
   * Use the jQuery DataTables-plugin to add
   * interactive features to your tables.
   */
   
  $(".datatable").dataTable();
  
//  /**
//   * Datepicker JQuery
//   */
//  $( ".datepicker" ).datepicker();
    
  /**
   * Use search in real-time 
   **/
  $("#search").liveSearch({url: '/search?q='}); // Edit this url to match your search action
  // Nice animation on focus
  $("#search").focus(function() { $(this).animate({ width: '175px' }) });
  $("#search").blur(function() {
    if($(this).val() == "") { // Only go back to normal when nothing's filled in
      $(this).animate({ width: '100px' })
    }
  });
  
  /**
   * Tags in input fields
   */
  $('.tags').tagsInput();
  
  /**
   * Placeholders in forms
  $('input[type="text"]').placeholderFunction('input-focused');
   */
   
  
  /**
   * Skin select boxes, checkboxes and radiobuttons
   */
   
  //$('select').select_skin();	//hier ist die Ursache fuer die mehrfach verschachtelten select boxen durch den immer wieder kehrenden Aufruf --> deshalb Verlagerung in $('document').ready (Aufruf nur bei Neuladen der Gesamtseite) 
  $('input[type=checkbox], input[type=radio]').prettyCheckboxes();
  
  /**
   * Functional secondary menu using tabs
   */
  
  $(".tab").hide();
  
  if($("nav#secondary ul li.current").length < 1) {
    $("nav#secondary ul li:first-child").addClass("current");    
  }
  
  var link = $("nav#secondary ul li.current a").attr("href");
  $(link).show();
  
  $("nav#secondary ul li a").click(function() {
    if(!$(this).hasClass("current")) {
      $("nav#secondary ul li").removeClass("current");
      $(this).parent().addClass("current");
      $(".tab").hide();
      var link = $(this).attr("href");
      $(link).show();
      initBackground();
    }
    return false;
  });
  
  /**
   * Gallery on hover
   */
   
  $(".gallery img").wrap("<div class=\"image\">");
  $(".gallery .image").append('<div class="overlay"></div><a href="#" class="button icon search">View</a>');
  $(".gallery .image").hover(function() {
    $(this).find("a").stop().animate({ opacity: 1}, 200);
    $(this).find(".overlay").stop().animate({ opacity: .5}, 200);
  }, function() {
    $(this).find("a").stop().animate({ opacity: 0}, 200);
    $(this).find(".overlay").stop().animate({ opacity: 0}, 200);
  });
  
  /**
   * Wysiwym-editor
   */
   
  $('.wysiwym').wymeditor({
    logoHtml: '',
    toolsItems: [
      {'name': 'Bold', 'title': 'Strong', 'css': 'wym_tools_strong'}, 
      {'name': 'Italic', 'title': 'Emphasis', 'css': 'wym_tools_emphasis'},
      {'name': 'InsertOrderedList', 'title': 'Ordered_List',
        'css': 'wym_tools_ordered_list'},
      {'name': 'InsertUnorderedList', 'title': 'Unordered_List',
        'css': 'wym_tools_unordered_list'},
      {'name': 'Indent', 'title': 'Indent', 'css': 'wym_tools_indent'},
      {'name': 'Outdent', 'title': 'Outdent', 'css': 'wym_tools_outdent'},
      {'name': 'CreateLink', 'title': 'Link', 'css': 'wym_tools_link'},
      {'name': 'Paste', 'title': 'Paste_From_Word', 'css': 'wym_tools_paste'},
      {'name': 'Undo', 'title': 'Undo', 'css': 'wym_tools_undo'},
      {'name': 'Redo', 'title': 'Redo', 'css': 'wym_tools_redo'}
    ],
    containersItems: [
      {'name': 'P', 'title': 'Paragraph', 'css': 'wym_containers_p'},
      {'name': 'H4', 'title': 'Heading_4', 'css': 'wym_containers_h4'}
    ]
  });
  
  /** 
   * Dynamically create charts from tables
   * Just add class="linechart" and replace
   * 'line' with any type of chart.
   */
   
  // This array contains the colors that will be used in charts
  var colors = ['#005ba8','#1175c9',
                '#92d5ea','#ee8310',
                '#8d10ee','#5a3b16',
                '#26a4ed','#f45a90',
                '#e9e744'];
                
  $('.barchart').visualize({ type: 'bar', colors: colors });
  $('.linechart').visualize({ type: 'line', lineWeight: 2, colors: colors });
  $('.areachart').visualize({ type: 'area', lineWeight: 1, colors: colors });
  $('.piechart').visualize({ type: 'pie', colors: colors });
  $('.barchart, .linechart, .areachart, .piechart').hide();
  
  /**
   * Make sure the background gradient reaches
   * the bottom of the page.
   */
  function initBackground() {
    if($('#container').height() < window.innerHeight) {
      $('#container').height(window.innerHeight);
    }
  }
  
  initBackground();
  
  $('#menu li ul').css({
	  display: "none",
	  left: "auto"
  });
  
  $('#menu li').hover(function() {
	  $(this).find('ul').stop(true, true).slideDown('fast');
  },
  function() {
	  $(this).find('ul').stop(true, true).fadeOut('fast');
  });
  
};

	function getWidth() {
		var de = document.documentElement;
		var myWidth = window.innerWidth || self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth;
		return myWidth;
	}
	document.getWidth=getWidth;

	function getHeight() {
		var de = document.documentElement;
		var myHeight = window.innerHeight || self.innerHeight || (de&&de.clientHeight) || document.body.clientHeight;
		return myHeight;
	}
	document.getHeight=getHeight;
	
	function adaptDocumentIframe() {
		var calculatedWidth = document.getWidth() * 0.48;
		var calculatedHeight = document.getHeight() * 0.9;
		var calculatedMargin = document.getWidth() * 0.03;
		
		$("#dociframe").css('min-width',calculatedWidth);
		$("#dociframe").css('min-height',calculatedHeight);
		$("#dociframe").css('margin-left',calculatedMargin);
	}


$('document').ready(function() {
	loadDefaults();
	$('select').select_skin();
	
	adaptDocumentIframe();
});